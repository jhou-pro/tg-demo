package jhou.asset;

import jhou.tablecodes.AssetStatus;
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
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Asset Number")
@CompanionObject(IAsset.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
public class Asset extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Asset.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Number", desc = "A unique asset number, auto-generated.")
    @CompositeKeyMember(1)
    @Readonly
    private String number;

    @IsProperty
    @MapTo
    @Title("Status")
    private AssetStatus status;
    
    @IsProperty
    @MapTo
    @Title("Parent")
    private Asset parent;
    
    @IsProperty
    @MapTo
    @Title("Peer")
    private Asset peer;
    
    @Override
    @Observable
    public Asset setActive(final boolean active) {
        return (Asset) super.setActive(active);
    }
    
    @Observable
    public Asset setNumber(final String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

    @Observable
    public Asset setPeer(final Asset peer) {
        this.peer = peer;
        return this;
    }

    public Asset getPeer() {
        return peer;
    }

    @Observable
    public Asset setParent(final Asset parent) {
        this.parent = parent;
        return this;
    }

    public Asset getParent() {
        return parent;
    }

    @Observable
    public Asset setStatus(final AssetStatus status) {
        this.status = status;
        return this;
    }

    public AssetStatus getStatus() {
        return status;
    }

    @Override
    @Observable
    public Asset setDesc(final String desc) {
        return (Asset) super.setDesc(desc);
    }

}
