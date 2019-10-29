package jhou.tablecodes;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetStatus}.
 *
 * @author Developers
 *
 */
@EntityType(AssetStatus.class)
public class AssetStatusDao extends CommonEntityDao<AssetStatus> implements IAssetStatus {

    @Inject
    public AssetStatusDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetStatus> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetStatus.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetStatus.FETCH_PROVIDER");
    }
}
