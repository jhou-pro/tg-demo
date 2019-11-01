package jhou.dev_mod.util;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

import jhou.asset.Asset;
import jhou.asset.AssetCertification;
import jhou.asset.Certification;
import jhou.config.ApplicationDomain;
import jhou.personnel.Person;
import jhou.tablecodes.AssetClass;
import jhou.tablecodes.AssetStatus;
import ua.com.fielden.platform.devdb_support.DomainDrivenDataPopulation;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.persistence.HibernateUtil;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.IDomainDrivenTestCaseConfiguration;
import ua.com.fielden.platform.utils.DbUtils;

/**
 * This is a convenience class for (re-)creation of the development database and its population.
 * 
 * It contains the <code>main</code> method and can be executed whenever the target database needs to be (re-)set.
 * <p>
 * 
 * <b>IMPORTANT: </b><i>One should be careful not to run this code against the deployment or production databases, which would lead to the loss of all data.</i>
 * 
 * <p>
 * 
 * @author TG Team
 * 
 */
public class PopulateDb extends DomainDrivenDataPopulation {
    private static final Logger LOGGER = Logger.getLogger(PopulateDb.class);

    private final ApplicationDomain applicationDomainProvider = new ApplicationDomain();

    private PopulateDb(final IDomainDrivenTestCaseConfiguration config, final Properties props) {
        super(config, props);
    }

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Initialising...");
        final String configFileName = args.length == 1 ? args[0] : "application.properties";
        final Properties props = new Properties();
        try (final FileInputStream in = new FileInputStream(configFileName)) {
            props.load(in);
        }
        
        DOMConfigurator.configure(props.getProperty("log4j"));

        LOGGER.info("Obtaining Hibernate dialect...");
        final Class<?> dialectType = Class.forName(props.getProperty("hibernate.dialect"));
        final Dialect dialect = (Dialect) dialectType.newInstance();
        LOGGER.info(format("Running with dialect %s...", dialect));
        final DataPopulationConfig config = new DataPopulationConfig(props);
        LOGGER.info("Generating DDL and running it against the target DB...");

        // use TG DDL generation or
        // Hibernate DDL generation final List<String> createDdl = DbUtils.generateSchemaByHibernate()
        final List<String> createDdl = config.getDomainMetadata().generateDatabaseDdl(dialect);
        final List<String> ddl = dialect instanceof H2Dialect ?
                                 DbUtils.prependDropDdlForH2(createDdl) :
                                 DbUtils.prependDropDdlForSqlServer(createDdl);
        DbUtils.execSql(ddl, config.getInstance(HibernateUtil.class).getSessionFactory().getCurrentSession());

        final PopulateDb popDb = new PopulateDb(config, props);
        popDb.populateDomain();
    }

    @Override
    protected void populateDomain() {
        LOGGER.info("Creating and populating the development database...");
        
        setupUser(User.system_users.SU, "jhou");
        setupPerson(User.system_users.SU, "jhou");
        
        final AssetStatus oStatus = save(new_(AssetStatus.class, "O").setDesc("Operational."));
        final AssetStatus xStatus = save(new_(AssetStatus.class, "X").setDesc("Disposed."));
        final Asset asset1 = save(new_composite(Asset.class, "A1").setDesc("Asset 1.").setStatus(oStatus).setActive(true));
        final Asset asset2 = save(new_composite(Asset.class, "A2").setDesc("Asset 2.").setStatus(xStatus).setParent(asset1).setActive(true));
        final Asset asset3 = save(new_composite(Asset.class, "A3").setDesc("Asset 3.").setStatus(oStatus).setParent(asset1).setPeer(asset2).setActive(true));
        save(asset2.setPeer(asset3));
        final Certification cert1 = save(new_(Certification.class, "C1").setDesc("Certification 1."));
        final Certification cert2 = save(new_(Certification.class, "C2").setDesc("Certification 2."));
        save(new_composite(AssetCertification.class, asset1, cert1));
        save(new_composite(AssetCertification.class, asset1, cert2));
        
        save(new_composite(AssetClass.class, "AC1").setDesc("Asset Class 1.").setActive(true));
        save(new_composite(AssetClass.class, "AC2").setDesc("Asset Class 2."));
        
        LOGGER.info("Completed database creation and population.");
    }

    private void setupPerson(final User.system_users defaultUser, final String emailDomain) {
        final User su = co(User.class).findByKey(defaultUser.name());
        save(new_(Person.class, defaultUser.name()).setActive(true).setUser(su).setDesc("Person who is a user").setEmail(defaultUser + "@" + emailDomain));
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
