package jhou.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.IFilter;
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
        return FETCH_PROVIDER;
    }
}
