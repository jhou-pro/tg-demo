package jhou.asset.actions;

import static ua.com.fielden.platform.entity.NoKey.NO_KEY;

import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.NoKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(NoKey.class)
@CompanionObject(IOpenRelatedAssetsAction.class)
public class OpenRelatedAssetsAction extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenRelatedAssetsAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    protected OpenRelatedAssetsAction() {
        setKey(NO_KEY);
    }
    
}
