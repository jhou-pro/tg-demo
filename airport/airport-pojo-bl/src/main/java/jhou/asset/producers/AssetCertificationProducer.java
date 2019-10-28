package jhou.asset.producers;

import com.google.inject.Inject;

import jhou.asset.Asset;
import jhou.asset.AssetCertification;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link AssetCertification}.
 *
 * @author Developers
 *
 */
public class AssetCertificationProducer extends DefaultEntityProducerWithContext<AssetCertification> {

    @Inject
    public AssetCertificationProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, AssetCertification.class, coFinder);
    }

    @Override
    protected AssetCertification provideDefaultValuesForStandardNew(final AssetCertification entityIn, final EntityNewAction masterEntity) {
        final AssetCertification entityOut = super.provideDefaultValuesForStandardNew(entityIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre
        // 2. Centre embedded in Asset Master
        // In the second case we want to default the asset and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(Asset.class)) {
            final Asset shallowAsset = ofMasterEntity().keyOfMasterEntity(Asset.class);
            // shallowAsset has been fetched in OpenAssetMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for CocEntry
            entityOut.setAsset(refetch(shallowAsset, "asset"));
            entityOut.getProperty("asset").validationResult().ifFailure(Result::throwRuntime);
            entityOut.getProperty("asset").setEditable(false);
        }
        return entityOut;
    }
}
