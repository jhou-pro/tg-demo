package jhou.webapp.config.asset;

import static java.lang.String.format;
import static jhou.common.StandardActions.actionAddDesc;
import static jhou.common.StandardActions.actionEditDesc;
import static jhou.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static jhou.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao.enhanceEmbededCentreQuery;
import static ua.com.fielden.platform.entity_centre.review.DynamicQueryBuilder.createConditionProperty;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static ua.com.fielden.platform.web.centre.api.actions.impl.EntityActionBuilder.action;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;

import java.util.Optional;

import com.google.inject.Injector;

import jhou.asset.Asset;
import jhou.asset.AssetCertification;
import jhou.asset.Certification;
import jhou.asset.actions.DisposeAssetAction;
import jhou.asset.master.menu.actions.AssetMaster_OpenAssetCertification_MenuItem;
import jhou.asset.master.menu.actions.AssetMaster_OpenMain_MenuItem;
import jhou.asset.ui_actions.OpenAssetMasterAction;
import jhou.asset.ui_actions.producers.OpenAssetMasterActionProducer;
import jhou.common.LayoutComposer;
import jhou.common.StandardActions;
import jhou.main.menu.asset.MiAsset;
import jhou.main.menu.asset.MiAssetMaster_AssetCertification;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.ICompleted;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.IWhere0;
import ua.com.fielden.platform.web.PrefDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.CentreContext;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.IQueryEnhancer;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.compound.Compound;
import ua.com.fielden.platform.web.view.master.api.compound.impl.CompoundMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;

/**
 * {@link Asset} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetWebUiConfig {

    private final Injector injector;

    public final EntityCentre<Asset> centre;
    public final EntityMaster<Asset> master;
    public final EntityMaster<OpenAssetMasterAction> compoundMaster;
    public final EntityActionConfig editAssetAction;
    private final EntityActionConfig newAssetAction;

    public static AssetWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetWebUiConfig(injector, builder);
    }

    private AssetWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editAssetAction = Compound.openEdit(OpenAssetMasterAction.class, Asset.ENTITY_TITLE, actionEditDesc(Asset.ENTITY_TITLE), dims);
        newAssetAction = Compound.openNew(OpenAssetMasterAction.class, "add-circle-outline", Asset.ENTITY_TITLE, actionAddDesc(Asset.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(Asset.class, editAssetAction);

        centre = createAssetCentre(builder);
        builder.register(centre);

        master = createAssetMaster();
        builder.register(master);

        compoundMaster = CompoundMasterBuilder.<Asset, OpenAssetMasterAction>create(injector, builder)
            .forEntity(OpenAssetMasterAction.class)
            .withProducer(OpenAssetMasterActionProducer.class)
            .addMenuItem(AssetMaster_OpenMain_MenuItem.class)
                .icon("icons:picture-in-picture")
                .shortDesc(OpenAssetMasterAction.MAIN)
                .longDesc(Asset.ENTITY_TITLE + " main")
                .withView(master)
            .also()
            .addMenuItem(AssetMaster_OpenAssetCertification_MenuItem.class)
                .icon("icons:view-module")
                .shortDesc(OpenAssetMasterAction.ASSETCERTIFICATIONS)
                .longDesc(Asset.ENTITY_TITLE + " " + OpenAssetMasterAction.ASSETCERTIFICATIONS)
                .withView(createAssetCertificationCentre())
            .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity centre for {@link Asset}.
     *
     * @return
     */
    private EntityCentre<Asset> createAssetCentre(final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 2);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        final EntityActionConfig disposeAssetAction = action(DisposeAssetAction.class)
                .withContext(context().withSelectionCrit().withSelectedEntities().build())
                .icon("icons:done-all")
                .shortDesc("Dispose Assets")
                .longDesc("Batch dispose assets (either manually selected or matching the selection criteria).")
                .build();

        final EntityCentreConfig<Asset> ecc = EntityCentreBuilder.centreFor(Asset.class)
                .runAutomatically()
                .addFrontAction(newAssetAction)
                .addTopAction(newAssetAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction).also()
                .addTopAction(disposeAssetAction)
                .addCrit("this").asMulti().autocompleter(Asset.class).also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Asset.ENTITY_TITLE))
                    .withAction(editAssetAction).also()
                .addProp("desc").minWidth(300).also()
                .addProp("status").minWidth(300)
                .addPrimaryAction(editAssetAction)
                .build();

        return new EntityCentre<>(MiAsset.class, MiAsset.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link Asset}.
     *
     * @return
     */
    private EntityMaster<Asset> createAssetMaster() {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(3, 1);

        final IMaster<Asset> masterConfig = new SimpleMasterBuilder<Asset>().forEntity(Asset.class)
                .addProp("key").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addProp("status").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<>(Asset.class, masterConfig, injector);
    }

    private EntityCentre<AssetCertification> createAssetCertificationCentre() {
        final Class<AssetCertification> root = AssetCertification.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(AssetCertification.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetCertification.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(AssetCertification.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetCertification.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<AssetCertification> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("asset").asMulti().autocompleter(Asset.class).also()
                .addCrit("certification").asMulti().autocompleter(Certification.class).also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardEmbeddedScrollingConfig(0))
                .addProp("asset").order(1).asc().minWidth(80)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetCertification.ENTITY_TITLE)).also()
                .addProp("certification").minWidth(80).also()
                .addProp("desc").minWidth(80)
                .addPrimaryAction(standardEditAction)
                .setQueryEnhancer(AssetMaster_AssetCertificationCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiAssetMaster_AssetCertification.class, MiAssetMaster_AssetCertification.class.getSimpleName(), ecc, injector, null);
    }

    private static class AssetMaster_AssetCertificationCentre_QueryEnhancer implements IQueryEnhancer<AssetCertification> {
        @Override
        public ICompleted<AssetCertification> enhanceQuery(final IWhere0<AssetCertification> where, final Optional<CentreContext<AssetCertification, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty("asset"), context.get().getMasterEntity().getKey());
        }
    }

}