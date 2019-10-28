package jhou.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IDisposeAssetAction}.
 *
 * @author Developers
 *
 */
@EntityType(DisposeAssetAction.class)
public class DisposeAssetActionDao extends CommonEntityDao<DisposeAssetAction> implements IDisposeAssetAction {

    @Inject
    public DisposeAssetActionDao(final IFilter filter) {
        super(filter);
    }

}
