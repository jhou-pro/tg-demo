package jhou.ioc;

import ua.com.fielden.platform.domaintree.centre.ICentreDomainTreeManager.ICentreDomainTreeManagerAndEnhancer;
import ua.com.fielden.platform.entity.AbstractEntity;

public interface ITestCentreProvider {

    ICentreDomainTreeManagerAndEnhancer getCentre(final Class<? extends AbstractEntity<?>> type);

    ITestCentreProvider addCentre(final Class<? extends AbstractEntity<?>> type, final ICentreDomainTreeManagerAndEnhancer centre);
}
