package org.wahlzeit.utils.conditions;

public class Preconditions {

    static public void assertNotNullArgument(Object obj) {
        assertNotNullArgument(obj, "argument must not be null");
    }

    static public void assertNotNullArgument(Object obj, String message) {
        if (obj == null)
            throw new IllegalArgumentException(message);
    }

    static public void assertNonNegativeArgument(double value, String message) {
        if (value < 0)
            throw new IllegalArgumentException(message);
    }

    static public void assertNonNegativeArgument(double value) {
        assertNonNegativeArgument(value, "argument must not be negative");
    }

    static public void assertArgumentNotNan(double value) {
        assertArgumentNotNan(value, "the argument must not be Nan");
    }

    static public void assertArgumentNotNan(double value, String message) {
        if (Double.isNaN(value))
            throw new IllegalArgumentException(message);
    }

    // upper is exclusive
    static public void assertArgumentWithinRange(double value, double lower, double upper, String message) {
        if (value < lower || value >= upper)
            throw new IllegalArgumentException(message);
    }
}
