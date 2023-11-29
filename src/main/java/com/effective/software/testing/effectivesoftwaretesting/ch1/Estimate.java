package com.effective.software.testing.effectivesoftwaretesting.ch1;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Estimate {
    private final String developer;
    private final int estimate;

    public Estimate(String developer, int estimate) {
        this.developer = developer;
        this.estimate = estimate;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getEstimate() {
        return estimate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estimate estimate1)) return false;
        return estimate == estimate1.estimate && Objects.equals(developer, estimate1.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developer, estimate);
    }
}
