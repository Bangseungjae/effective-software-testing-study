package com.effective.software.testing.effectivesoftwaretesting.ch1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanningPoker {
    public List<String> identifyExtremes(List<Estimate> estimates) throws IllegalAccessException {
        Estimate lowerstEstimate = null;
        Estimate highestEstimate = null;

        if (estimates == null) {
            throw new IllegalArgumentException("estimates cannot be null");
        }
        if (estimates.size() <= 1) {
            throw new IllegalArgumentException("there has to be more than 1 estimate in the list");
        }

        for (Estimate estimate : estimates) {

            if (highestEstimate == null || estimate.getEstimate() > highestEstimate.getEstimate()) {
                highestEstimate = estimate;
            }
            if (lowerstEstimate == null || estimate.getEstimate() < lowerstEstimate.getEstimate()) {
                lowerstEstimate = estimate;
            }
        }

        if (lowerstEstimate.equals(highestEstimate)) {
            return Collections.emptyList();
        }

        return Arrays.asList(
                lowerstEstimate.getDeveloper(),
                highestEstimate.getDeveloper()
        );
    }
}
