package jhou.asset.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IOpenRelatedAssetsAction}.
 *
 * @author Developers
 *
 */
@EntityType(OpenRelatedAssetsAction.class)
public class OpenRelatedAssetsActionDao extends CommonEntityDao<OpenRelatedAssetsAction> implements IOpenRelatedAssetsAction {

    @Inject
    public OpenRelatedAssetsActionDao(final IFilter filter) {
        super(filter);
    }

}
