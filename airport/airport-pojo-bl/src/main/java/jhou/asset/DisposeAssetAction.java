package jhou.asset;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.NoKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.functional.centre.CentreContextHolder;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(NoKey.class)
@CompanionObject(IDisposeAssetAction.class)
public class DisposeAssetAction extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(DisposeAssetAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @Title(value = "Dispose all?", desc = "Dispose all assets that match the selection criteria and appear in the centre?")
    private boolean disposeAll = false;
    
    @IsProperty
    @Title(value = "Dispose selected?", desc = "Dispose only explicitly selected assets?")
    private boolean disposeSelected = false;
    
    @IsProperty(Long.class)
    private final Set<Long> selectedEntityIds = new HashSet<>();
    
    @IsProperty
    private CentreContextHolder contextHolder;

    @Observable
    public DisposeAssetAction setContextHolder(final CentreContextHolder contextHolder) {
        this.contextHolder = contextHolder;
        return this;
    }

    public CentreContextHolder getContextHolder() {
        return contextHolder;
    }

    @Observable
    protected DisposeAssetAction setSelectedEntityIds(final Set<Long> selectedEntityIds) {
        this.selectedEntityIds.clear();
        this.selectedEntityIds.addAll(selectedEntityIds);
        return this;
    }

    public Set<Long> getSelectedEntityIds() {
        return Collections.unmodifiableSet(selectedEntityIds);
    }

    @Observable
    public DisposeAssetAction setDisposeSelected(final boolean disposeSelected) {
        this.disposeSelected = disposeSelected;
        return this;
    }

    public boolean getDisposeSelected() {
        return disposeSelected;
    }

    @Observable
    public DisposeAssetAction setDisposeAll(final boolean disposeAll) {
        this.disposeAll = disposeAll;
        return this;
    }

    public boolean getDisposeAll() {
        return disposeAll;
    }
}
