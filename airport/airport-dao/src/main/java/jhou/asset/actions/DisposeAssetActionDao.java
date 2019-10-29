package jhou.asset.actions;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.util.stream.Stream;

import com.google.inject.Inject;

import jhou.asset.Asset;
import jhou.tablecodes.AssetStatus;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.web.utils.ICriteriaEntityRestorer;
/**
 * DAO implementation for companion object {@link IDisposeAssetAction}.
 *
 * @author Developers
 *
 */
@EntityType(DisposeAssetAction.class)
public class DisposeAssetActionDao extends CommonEntityDao<DisposeAssetAction> implements IDisposeAssetAction {

    private final ICriteriaEntityRestorer criteriaEntityRestorer;

    @Inject
    public DisposeAssetActionDao(final IFilter filter, final ICriteriaEntityRestorer criteriaEntityRestorer) {
        super(filter);
        this.criteriaEntityRestorer = criteriaEntityRestorer;
    }

    @Override
    @SessionRequired
    public DisposeAssetAction save(final DisposeAssetAction entity) {
        final EntityResultQueryModel<Asset> query;
        if (entity.isDisposeAll()) {
            query = select(Asset.class).where().prop("id").in() // query based on selection crit
                    .model(
                        criteriaEntityRestorer.restoreCriteriaEntity(entity.getContextHolder())
                        .createQuery().model()
                    ).model();
        } else {
            query = select(Asset.class).where().prop("id").in() // query based on selected entities
                    .values(
                        entity.getSelectedEntityIds().toArray()
                    ).model();
        }
        final fetch<Asset> assetFetch = co$(Asset.class).getFetchProvider().fetchModel(); // fetch model 
        final AssetStatus statusX = co(AssetStatus.class).findByKey("X"); // 'disposed status'
        try (final Stream<Asset> stream = co$(Asset.class).stream(from(query).with(assetFetch).model())) {
            stream.forEach(asset -> { // stream assets not to blow up heap for large numbers of entities
                asset.setStatus(statusX);
                if (asset.isValid().isSuccessful()) {
                    co$(Asset.class).save(asset);
                }
            });
        }
        return super.save(entity);
    }

}
