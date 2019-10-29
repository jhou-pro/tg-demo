package jhou.asset.actions.producers;

import com.google.inject.Inject;

import jhou.asset.actions.DisposeAssetAction;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
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
        if (contextNotEmpty()) {
            // take selectionCrit's context holder and initialise functional entity's corresponding property
            entity.setContextHolder(selectionCrit().centreContextHolder());
            if (selectedEntitiesNotEmpty()) {
                // store IDs of selected entities if there are any
                entity.setSelectedEntityIds(selectedEntityIds());
                // check `Dispose selected?` checkbox by default
                entity.setDisposeSelected(true);
            } else {
                // check `Dispose All?` checkbox by default
                entity.setDisposeAll(true);
            }
        }
        // the context is not present only if Cancel button is being tapped on action master -- no properties should be initialised
        return entity;
    }
}
