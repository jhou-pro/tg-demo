package jhou.security.tokens.open_compound_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import jhou.asset.ui_actions.OpenAssetMasterAction;
/**
 * A security token for entity {@link OpenAssetMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenAssetMasterAction_CanOpen_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenAssetMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenAssetMasterAction.ENTITY_TITLE);
}
