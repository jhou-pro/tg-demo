package jhou.ioc;

import com.google.inject.Inject;

import ua.com.fielden.platform.criteria.generator.ICriteriaGenerator;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.functional.centre.CentreContextHolder;
import ua.com.fielden.platform.entity_centre.review.criteria.EnhancedCentreEntityQueryCriteria;
import ua.com.fielden.platform.web.utils.ICriteriaEntityRestorer;

public class CriteriaRestorerForTestingPurposes implements ICriteriaEntityRestorer {

    private final ICriteriaGenerator criteriaGenerator;
    private final ITestCentreProvider centreProvider;

    @Inject
    public CriteriaRestorerForTestingPurposes (final ICriteriaGenerator criteiraGenerator, final ITestCentreProvider centreProvider) {
        this.criteriaGenerator = criteiraGenerator;
        this.centreProvider = centreProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    public EnhancedCentreEntityQueryCriteria<?, ?> restoreCriteriaEntity(final CentreContextHolder centreContextHolder) {
        final Class<? extends AbstractEntity<?>> type = (Class<? extends AbstractEntity<?>>)centreContextHolder.getCustomObject().get("@@type");
        return criteriaGenerator.generateCentreQueryCriteria(type, centreProvider.getCentre(type));
    }

}
