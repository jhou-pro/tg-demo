package jhou.webapp;

import org.apache.commons.lang.StringUtils;

import jhou.config.personnel.PersonWebUiConfig;
import jhou.webapp.config.asset.AssetCertificationWebUiConfig;
import jhou.webapp.config.asset.AssetWebUiConfig;
import jhou.webapp.config.asset.actions.DisposeAssetActionWebUiConfig;
import jhou.webapp.config.asset.actions.ImportDescActionWebUiConfig;
import jhou.webapp.config.asset.actions.OpenRelatedAssetsActionWebUiConfig;
import jhou.webapp.config.tablecodes.AssetClassWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 *
 */
public class WebUiConfig extends AbstractWebUiConfig {

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("Jhou Airport Asset Management", workflow, new String[] { "jhou/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }


    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();
        builder.setDateFormat("DD/MM/YYYY").setTimeFormat("HH:mm").setTimeWithMillisFormat("HH:mm:ss")
        // Minimum tablet width defines the boundary below which mobile layout takes place.
        // For example for Xiaomi Redmi 4x has official screen size of 1280x640,
        // still its viewport sizes is twice lesser: 640 in landscape orientation and 360 in portrait orientation.
        // When calculating reasonable transition tablet->mobile we need to consider "real" viewport sizes instead of physical pixel sizes (http://viewportsizes.com).
        .setMinTabletWidth(600);

        // Personnel
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);
        final UserWebUiConfig userWebUiConfig = new UserWebUiConfig(injector());
        final UserRoleWebUiConfig userRoleWebUiConfig = new UserRoleWebUiConfig(injector());
        final AssetClassWebUiConfig assetClassWebUiConfig = AssetClassWebUiConfig.register(injector(), builder);
        final AssetWebUiConfig assetWebUiConfig = AssetWebUiConfig.register(injector(), builder);
        DisposeAssetActionWebUiConfig.register(injector(), builder);
        AssetCertificationWebUiConfig.register(injector(), builder);
        ImportDescActionWebUiConfig.register(injector(), builder);
        OpenRelatedAssetsActionWebUiConfig.register(injector(), builder);

        // Configure application web resources such as masters and centres
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        // user/personnel module
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu().
            addModule("Asset Acquisition").
                description("Asset Acquisition description.").
                icon("mainMenu:help").
                detailIcon("mainMenu:help").
                bgColor("#FFE680").
                captionBgColor("#FFD42A").menu()
                .addMenuItem("Asset").description("Asset centre").centre(assetWebUiConfig.centre).done()
            .done().done().
            addModule("Table Codes").
                description("Table Codes description.").
                icon("mainMenu:help").
                detailIcon("mainMenu:help").
                bgColor("#FFE680").
                captionBgColor("#FFD42A").menu()
                .addMenuItem("Asset Class").description("Asset Class centre").centre(assetClassWebUiConfig.centre).done()
            .done().done().
            addModule("Users / Personnel").
                description("Provides functionality for managing application security and personnel data.").
                icon("mainMenu:help").
                detailIcon("mainMenu:help").
                bgColor("#FFE680").
                captionBgColor("#FFD42A").menu()
                .addMenuItem("Personnel").description("Personnel related data")
                    .addMenuItem("Personnel").description("Personnel Centre").centre(personWebUiConfig.centre).done()
                .done()
                .addMenuItem("Users").description("Users related data")
                    .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
                    .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
                .done()
            .done().done()
        .setLayoutFor(Device.DESKTOP, null, "[[[{\"rowspan\": 2}], []], [[]]]")
        .setLayoutFor(Device.TABLET, null, "[[[]]]")
        .setLayoutFor(Device.MOBILE, null, "[[[]]]")
        .minCellWidth(100).minCellHeight(148).done();
    }

}
