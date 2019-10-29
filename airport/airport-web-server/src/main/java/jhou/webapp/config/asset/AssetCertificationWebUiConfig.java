package jhou.webapp.config.asset;

import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static ua.com.fielden.platform.web.centre.api.actions.impl.EntityActionBuilder.action;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;

import java.util.Optional;

import com.google.inject.Injector;

import jhou.asset.AssetCertification;
import jhou.asset.actions.ImportDescAction;
import jhou.asset.producers.AssetCertificationProducer;
import jhou.common.LayoutComposer;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.minijs.JsCode;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;

/**
 * {@link AssetCertification} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetCertificationWebUiConfig {

    public final EntityMaster<AssetCertification> master;

    public static AssetCertificationWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetCertificationWebUiConfig(injector, builder);
    }

    private AssetCertificationWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity master for {@link AssetCertification}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetCertification> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(3, 1);
        
        final EntityActionConfig importDescAction = action(ImportDescAction.class)
                .withContext(context().withMasterEntity().build())
                .postActionSuccess(() -> new JsCode("self.setEditorValue4Property('desc', functionalEntity, 'desc');"))
                .icon("icons:input")
                .longDesc("Import Description from related Asset")
                .withNoParentCentreRefresh()
                .build();
        
        final IMaster<AssetCertification> masterConfig = new SimpleMasterBuilder<AssetCertification>().forEntity(AssetCertification.class)
                .addProp("asset").asAutocompleter().also()
                .addProp("certification").asAutocompleter().also()
                .addProp("desc").asMultilineText().withAction(importDescAction).also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetCertification.class, AssetCertificationProducer.class, masterConfig, injector);
    }
}