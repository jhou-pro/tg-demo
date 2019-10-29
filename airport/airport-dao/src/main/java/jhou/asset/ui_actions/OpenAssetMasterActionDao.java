package jhou.asset.ui_actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import jhou.asset.AssetCertification;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IOpenAssetMasterAction}.
 *
 * @author Developers
 *
 */
@EntityType(OpenAssetMasterAction.class)
public class OpenAssetMasterActionDao extends AbstractOpenCompoundMasterDao<OpenAssetMasterAction> implements IOpenAssetMasterAction {

    @Inject
    public OpenAssetMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
        addViewBinding(OpenAssetMasterAction.ASSETCERTIFICATIONS, AssetCertification.class, "asset");
    }

    @Override
    protected IFetchProvider<OpenAssetMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
