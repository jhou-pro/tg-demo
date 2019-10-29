package jhou.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link ICertification}.
 *
 * @author Developers
 *
 */
@EntityType(Certification.class)
public class CertificationDao extends CommonEntityDao<Certification> implements ICertification {

    @Inject
    public CertificationDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<Certification> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in ICertification.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in ICertification.FETCH_PROVIDER");
    }
}
