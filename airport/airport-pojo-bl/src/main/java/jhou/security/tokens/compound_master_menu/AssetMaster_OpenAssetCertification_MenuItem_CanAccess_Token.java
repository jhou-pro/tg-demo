package jhou.security.tokens.compound_master_menu;

import static java.lang.String.format;

import jhou.asset.master.menu.actions.AssetMaster_OpenAssetCertification_MenuItem;
import ua.com.fielden.platform.security.tokens.Template;
/**
 * A security token for entity {@link AssetMaster_OpenAssetCertification_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class AssetMaster_OpenAssetCertification_MenuItem_CanAccess_Token extends AssetModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), AssetMaster_OpenAssetCertification_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), AssetMaster_OpenAssetCertification_MenuItem.ENTITY_TITLE);
}
