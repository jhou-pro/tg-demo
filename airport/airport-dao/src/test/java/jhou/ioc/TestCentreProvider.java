package jhou.ioc;

import java.util.HashMap;
import java.util.Map;

import ua.com.fielden.platform.domaintree.centre.ICentreDomainTreeManager.ICentreDomainTreeManagerAndEnhancer;
import ua.com.fielden.platform.entity.AbstractEntity;

public class TestCentreProvider implements ITestCentreProvider {

    private final Map<Class<? extends AbstractEntity<?>>, ICentreDomainTreeManagerAndEnhancer> centres = new HashMap<>();

    @Override
    public ICentreDomainTreeManagerAndEnhancer getCentre(final Class<? extends AbstractEntity<?>> type) {
        return centres.get(type);
    }

    @Override
    public ITestCentreProvider addCentre(final Class<? extends AbstractEntity<?>> type, final ICentreDomainTreeManagerAndEnhancer centre) {
        this.centres.put(type, centre);
        return this;
    }

}
