package jhou.tablecodes;

import jhou.tablecodes.validators.NoSpaceValidator;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@CompanionObject(IAssetClass.class)
@MapEntityTo
@KeyTitle("Asset Class")
@DescTitle("Asset Class Description")
@DisplayDescription
@DescRequired
public class AssetClass extends ActivatableAbstractEntity<DynamicEntityKey> {
    
    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetClass.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Name", desc = "Asset class name")
    @CompositeKeyMember(1)
    @BeforeChange(@Handler(NoSpaceValidator.class))
    private String name;
    
    @Observable
    public AssetClass setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    @Observable
    public AssetClass setDesc(final String desc) {
        return (AssetClass) super.setDesc(desc);
    }
    
}