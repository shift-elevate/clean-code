package com.cleancode.domain;

/**
 * Value object representing insurance details.
 * Encapsulates provider and policy number as related insurance data.
 */
public class InsuranceDetails {
    private final String provider;
    private final String policyNumber;

    public InsuranceDetails(String provider, String policyNumber) {
        if (provider == null || provider.trim().isEmpty()) {
            throw new IllegalArgumentException("Provider cannot be null or empty");
        }
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Policy number cannot be null or empty");
        }
        this.provider = provider.trim();
        this.policyNumber = policyNumber.trim();
    }

    public String getFormattedCoverage() {
        return String.format("%s (Policy: %s)", provider, policyNumber);
    }

    public String getProvider() {
        return provider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    @Override
    public String toString() {
        return getFormattedCoverage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceDetails that = (InsuranceDetails) o;
        return provider.equals(that.provider) && policyNumber.equals(that.policyNumber);
    }

    @Override
    public int hashCode() {
        return 31 * provider.hashCode() + policyNumber.hashCode();
    }
}
