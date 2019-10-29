package jhou.asset.actions.producers;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

import jhou.asset.actions.ImportDescAction;
/**
 * A producer for new instances of entity {@link ImportDescAction}.
 *
 * @author Developers
 *
 */
public class ImportDescActionProducer extends DefaultEntityProducerWithContext<ImportDescAction> {

    @Inject
    public ImportDescActionProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, ImportDescAction.class, coFinder);
    }

    @Override
    protected ImportDescAction provideDefaultValues(final ImportDescAction entity) {
        // TODO Context from producers should always be captured as entity properties.
        //      Producers provide context decomposition API - refer IContextDecomposer.
        //      For example to capture selected entities it is best to use method "selectedEntityIds",
        //      which returns a set of Long values and is much faster for marshaling than the fully fledged entities.
        return super.provideDefaultValues(entity);
    }
}
