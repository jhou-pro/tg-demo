package jhou.security.tokens.open_compound_master;

import static java.lang.String.format;

import jhou.asset.ui_actions.OpenAssetMasterAction;
import jhou.security.tokens.compound_master_menu.AssetModuleToken;
import ua.com.fielden.platform.security.tokens.Template;
/**
 * A security token for entity {@link OpenAssetMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenAssetMasterAction_CanOpen_Token extends AssetModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenAssetMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenAssetMasterAction.ENTITY_TITLE);
}
