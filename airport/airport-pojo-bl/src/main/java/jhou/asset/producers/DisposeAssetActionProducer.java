package jhou.asset.producers;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

import jhou.asset.DisposeAssetAction;
/**
 * A producer for new instances of entity {@link DisposeAssetAction}.
 *
 * @author Developers
 *
 */
public class DisposeAssetActionProducer extends DefaultEntityProducerWithContext<DisposeAssetAction> {

    @Inject
    public DisposeAssetActionProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, DisposeAssetAction.class, coFinder);
    }

    @Override
    protected DisposeAssetAction provideDefaultValues(final DisposeAssetAction entity) {
        // TODO Context from producers should always be captured as entity properties.
        //      Producers provide context decomposition API - refer IContextDecomposer.
        //      For example to capture selected entities it is best to use method "selectedEntityIds",
        //      which returns a set of Long values and is much faster for marshaling than the fully fledged entities.
        return super.provideDefaultValues(entity);
    }
}
