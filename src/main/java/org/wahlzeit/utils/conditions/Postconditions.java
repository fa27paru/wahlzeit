package org.wahlzeit.utils.conditions;

public class Postconditions { 
    static public void assertNonNegativeResult(double value) {
        assertNonNegativeResult(value, "the result must not be negative");
    }

    static public void assertNonNegativeResult(double value, String message) {
        if (value < 0)
            throw new IllegalResultException(message);
    }

    static public void assertResultNotNan(double value) {
        assertResultNotNan(value, "the result must not be Nan");
    }

    static public void assertResultNotNan(double value, String message) {
        if (Double.isNaN(value))
            throw new IllegalResultException(message);
    }

    // upper is exclusive
    static public void assertResultWithinRange(double value, double lower, double upper, String message) {
        if (value < lower || value >= upper)
            throw new IllegalResultException(message);
    }
}
