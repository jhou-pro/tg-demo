package jhou.asset.definers;

import jhou.asset.DisposeAssetAction;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;

public class DisposeAssetActionDisposeAllSelectedDefiner implements IAfterChangeEventHandler<Boolean> {

    @Override
    public void handle(final MetaProperty<Boolean> property, final Boolean newValue) {
        final DisposeAssetAction action = (DisposeAssetAction) property.getEntity();
        if (!action.isInitialising()) {
            if (action.isDisposeAll() == action.isDisposeSelected()) { // disposeAll and disposeSelected should be opposites
                if ("disposeAll".equals(property.getName())) {
                    action.setDisposeSelected(!action.isDisposeAll());
                } else {
                    action.setDisposeAll(!action.isDisposeSelected());
                }
            }
        }
    }

}