package jhou.ioc;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.inject.Scopes;

import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.security.IAuthorisationModel;
import ua.com.fielden.platform.serialisation.api.ISerialisationClassProvider;
import ua.com.fielden.platform.utils.IUniversalConstants;
import ua.com.fielden.platform.web.utils.ICriteriaEntityRestorer;

public class TestApplicationServerModule extends ApplicationServerModule {

    public TestApplicationServerModule(
            final Map<Class, Class> defaultHibernateTypes,
            final IApplicationDomainProvider applicationDomainProvider,
            final List<Class<? extends AbstractEntity<?>>> domainTypes,
            final Class<? extends ISerialisationClassProvider> serialisationClassProviderType,
            final Class<? extends IFilter> automaticDataFilterType,
            final Class<? extends IAuthorisationModel> authorisationModelType,
            final Class<? extends IUniversalConstants> universalConstantsType, final Properties props)
            throws Exception {
        super(defaultHibernateTypes, applicationDomainProvider, domainTypes, serialisationClassProviderType, automaticDataFilterType, authorisationModelType, universalConstantsType, props);
    }

    @Override
    protected void configure() {
        super.configure();

        bind(ITestCentreProvider.class).toInstance(new TestCentreProvider());
        bind(ICriteriaEntityRestorer.class).to(CriteriaRestorerForTestingPurposes.class).in(Scopes.SINGLETON);
    }
}
