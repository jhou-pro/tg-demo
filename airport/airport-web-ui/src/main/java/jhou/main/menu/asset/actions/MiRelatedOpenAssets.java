package jhou.main.menu.asset.actions;

import jhou.asset.Asset;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
/**
 * Main menu item representing an related Open Assets centre for {@link Asset} on Asset master by clicking on parent or peer action.
 *
 * @author Developers
 *
 */
@EntityType(Asset.class)
public class MiRelatedOpenAssets extends MiWithConfigurationSupport<Asset> {

}
