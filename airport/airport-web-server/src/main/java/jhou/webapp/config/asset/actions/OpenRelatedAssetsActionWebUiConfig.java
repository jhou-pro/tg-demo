package jhou.webapp.config.asset.actions;

import static java.lang.String.format;
import static jhou.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static ua.com.fielden.platform.entity.IContextDecomposer.decompose;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;

import java.util.Optional;

import com.google.inject.Injector;

import jhou.asset.Asset;
import jhou.asset.AssetCertification;
import jhou.asset.actions.OpenRelatedAssetsAction;
import jhou.asset.exceptions.AssetModuleException;
import jhou.common.LayoutComposer;
import jhou.common.StandardActions;
import jhou.main.menu.asset.actions.MiRelatedOpenAssets;
import jhou.tablecodes.AssetStatus;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.ICompleted;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.IWhere0;
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
import ua.com.fielden.platform.web.view.master.api.with_centre.impl.MasterWithCentreBuilder;

/**
 * {@link OpenRelatedAssetsAction} Web UI configuration.
 *
 * @author Developers
 *
 */
public class OpenRelatedAssetsActionWebUiConfig {

    public final EntityMaster<OpenRelatedAssetsAction> master;
    private final EntityCentre<Asset> relatedOpenAssetsCentre;

    public static OpenRelatedAssetsActionWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new OpenRelatedAssetsActionWebUiConfig(injector, builder);
    }

    private OpenRelatedAssetsActionWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        relatedOpenAssetsCentre = createRelatedOpenAssetsCentre(injector);
        builder.register(relatedOpenAssetsCentre);
        
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity master for {@link OpenRelatedAssetsAction}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<OpenRelatedAssetsAction> createMaster(final Injector injector) {
        return new EntityMaster<>(
            OpenRelatedAssetsAction.class,
            new MasterWithCentreBuilder<OpenRelatedAssetsAction>()
                .forEntityWithSaveOnActivate(OpenRelatedAssetsAction.class)
                .withCentre(relatedOpenAssetsCentre).done(),
            injector
        );
    }
    
    private EntityCentre<Asset> createRelatedOpenAssetsCentre(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        
        final EntityCentreConfig<Asset> centreConfig = EntityCentreBuilder.centreFor(Asset.class)
            .runAutomatically() // is needed to make the centre properly autorunnable
            .addTopAction(standardNewAction).also()
            .addTopAction(standardDeleteAction).also()
            .addTopAction(standardSortAction).also()
            .addTopAction(standardExportAction)
            .addCrit("this").asMulti().autocompleter(Asset.class).also()
            .addCrit("desc").asMulti().text().also()
            .addCrit("status").asMulti().autocompleter(AssetStatus.class).also()
            .addCrit("parent").asMulti().autocompleter(Asset.class).also()
            .addCrit("peer").asMulti().autocompleter(Asset.class)
            .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
            .setLayoutFor(Device.TABLET, Optional.empty(), layout)
            .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
            .withScrollingConfig(standardEmbeddedScrollingConfig(0))
            .addProp("key").order(1).asc().minWidth(80)
                .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetCertification.ENTITY_TITLE)).also()
            .addProp("desc").minWidth(80).also()
            .addProp("status").minWidth(80).also()
            .addProp("parent").minWidth(80).also()
            .addProp("peer").minWidth(80)
            .addPrimaryAction(standardEditAction)
            .setQueryEnhancer(RelatedOpenAssetsCentreQueryEnhancer.class, context().withMasterEntity().build())
            .build();
        return new EntityCentre<>(MiRelatedOpenAssets.class, "MiRelatedOpenAssets", centreConfig, injector, null);
    }
    
    private static class RelatedOpenAssetsCentreQueryEnhancer implements IQueryEnhancer<Asset> {
        @Override
        public ICompleted<Asset> enhanceQuery(final IWhere0<Asset> where, final Optional<CentreContext<Asset, ?>> context) {
            final Asset asset = decompose(context).ofMasterEntity().masterEntity(Asset.class);
            final String property = decompose(context).ofMasterEntity().chosenProperty();
            if ("parent".equals(property)) {
                return where.prop("parent").eq().val(asset.getParent());
            } else if ("peer".equals(property)) {
                return where.prop("peer").eq().val(asset.getPeer());
            } else {
                throw new AssetModuleException(format("Action to open related open assets is not available for [%s] property.", property));
            }
        }
    }
    
}