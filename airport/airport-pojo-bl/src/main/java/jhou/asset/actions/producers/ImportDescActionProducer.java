package jhou.asset.actions.producers;

import com.google.inject.Inject;

import jhou.asset.AssetCertification;
import jhou.asset.actions.ImportDescAction;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
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
        if (contextNotEmpty()) {
            final AssetCertification masterEntity = masterEntity(AssetCertification.class); // we know exact type here
            entity.setDesc(masterEntity.getAsset().getDesc());
        }
        return super.provideDefaultValues(entity);
    }
}
