package jhou.webapp.config.asset.actions;

import static java.lang.String.format;
import static jhou.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import jhou.asset.actions.ImportDescAction;
import jhou.common.LayoutComposer;
import jhou.common.StandardActions;

import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
import jhou.asset.actions.producers.ImportDescActionProducer;
/**
 * {@link ImportDescAction} Web UI configuration.
 *
 * @author Developers
 *
 */
public class ImportDescActionWebUiConfig {

    public final EntityMaster<ImportDescAction> master;

    public static ImportDescActionWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new ImportDescActionWebUiConfig(injector, builder);
    }

    private ImportDescActionWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity master for {@link ImportDescAction}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<ImportDescAction> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 2);

        final IMaster<ImportDescAction> masterConfig = new SimpleMasterBuilder<ImportDescAction>().forEntity(ImportDescAction.class)
                .addProp("key").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(ImportDescAction.class, ImportDescActionProducer.class, masterConfig, injector);
    }
}