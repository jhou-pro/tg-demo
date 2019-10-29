package jhou.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetCertification}.
 *
 * @author Developers
 *
 */
@EntityType(AssetCertification.class)
public class AssetCertificationDao extends CommonEntityDao<AssetCertification> implements IAssetCertification {

    @Inject
    public AssetCertificationDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetCertification> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetCertification.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetCertification.FETCH_PROVIDER");
    }
}
