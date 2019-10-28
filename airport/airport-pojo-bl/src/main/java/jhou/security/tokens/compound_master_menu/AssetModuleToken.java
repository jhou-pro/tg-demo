package jhou.security.tokens.compound_master_menu;

import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.security.ISecurityToken;

/**
 * Asset module security token.
 *
 * @author Developers
 *
 */
@KeyTitle(value = "Asset Module", desc = "Controls permission to select and review asset data.")
public class AssetModuleToken implements ISecurityToken {
}
