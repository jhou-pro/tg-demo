package jhou.asset.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IImportDescAction}.
 *
 * @author Developers
 *
 */
@EntityType(ImportDescAction.class)
public class ImportDescActionDao extends CommonEntityDao<ImportDescAction> implements IImportDescAction {

    @Inject
    public ImportDescActionDao(final IFilter filter) {
        super(filter);
    }

}
