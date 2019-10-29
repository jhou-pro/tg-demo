package jhou.webapp.config.asset.actions;

import static ua.com.fielden.platform.web.view.master.EntityMaster.noUiFunctionalMaster;

import com.google.inject.Injector;

import jhou.asset.actions.ImportDescAction;
import jhou.asset.actions.producers.ImportDescActionProducer;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.view.master.EntityMaster;
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
        return noUiFunctionalMaster(ImportDescAction.class, ImportDescActionProducer.class, injector);
    }
}