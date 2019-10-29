package jhou.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jhou.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import jhou.asset.Asset;
import jhou.tablecodes.AssetClass;
import jhou.tablecodes.AssetStatus;
import jhou.asset.Certification;
import jhou.asset.actions.DisposeAssetAction;
import jhou.asset.AssetCertification;
import jhou.asset.ui_actions.OpenAssetMasterAction;
import jhou.asset.master.menu.actions.AssetMaster_OpenMain_MenuItem;
import jhou.asset.master.menu.actions.AssetMaster_OpenAssetCertification_MenuItem;
import jhou.asset.actions.ImportDescAction;
import jhou.asset.actions.OpenRelatedAssetsAction;

/**
 * A class to register domain entities.
 * 
 * @author TG Team
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
    private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
    private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

    private static void add(final Class<? extends AbstractEntity<?>> domainType) {
        entityTypes.add(domainType);
        domainTypes.add(domainType);
    }

    /**
                                                 * This is a static initialisation block where all entity types should be registered.
                                                 */
    static {
        entityTypes.addAll(PlatformDomainTypes.types);
        add(Person.class);
        add(Asset.class);
        add(AssetClass.class);
        add(AssetStatus.class);
        add(Certification.class);
        add(AssetCertification.class);
        add(OpenAssetMasterAction.class);
        add(AssetMaster_OpenMain_MenuItem.class);
        add(AssetMaster_OpenAssetCertification_MenuItem.class);
        add(DisposeAssetAction.class);
        add(ImportDescAction.class);
        add(OpenRelatedAssetsAction.class);
    }

    @Override
    public List<Class<? extends AbstractEntity<?>>> entityTypes() {
        return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
    }

    public List<Class<? extends AbstractEntity<?>>> domainTypes() {
        return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
    }
}
