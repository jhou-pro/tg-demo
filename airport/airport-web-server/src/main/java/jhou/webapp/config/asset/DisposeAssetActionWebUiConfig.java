package jhou.webapp.config.asset;

import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import jhou.asset.DisposeAssetAction;
import jhou.asset.producers.DisposeAssetActionProducer;
import jhou.common.LayoutComposer;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
/**
 * {@link DisposeAssetAction} Web UI configuration.
 *
 * @author Developers
 *
 */
public class DisposeAssetActionWebUiConfig {

    public final EntityMaster<DisposeAssetAction> master;

    public static DisposeAssetActionWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new DisposeAssetActionWebUiConfig(injector, builder);
    }

    private DisposeAssetActionWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity master for {@link DisposeAssetAction}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<DisposeAssetAction> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(2, 1);

        final IMaster<DisposeAssetAction> masterConfig = new SimpleMasterBuilder<DisposeAssetAction>().forEntity(DisposeAssetAction.class)
                .addProp("disposeAll").asCheckbox().also()
                .addProp("disposeSelected").asCheckbox().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(DisposeAssetAction.class, DisposeAssetActionProducer.class, masterConfig, injector);
    }
}