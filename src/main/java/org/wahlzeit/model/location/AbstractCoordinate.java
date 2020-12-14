package org.wahlzeit.model.location;

public abstract class AbstractCoordinate implements Coordinate {
    @Override
    public double getCartesianDistance(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);

        double result = asCartesianCoordinate().getCartesianDistance(other);

        assertClassInvariants();
        return result;
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);

        double result = asSphericalCoordinate().getCentralAngle(other);

        assertClassInvariants();
        return result;
    }

    @Override
    public boolean isEqual(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);

        boolean result = asCartesianCoordinate().isEqual(other);

        assertClassInvariants();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        assertClassInvariants();

        boolean result;
        if (this == obj)
            result = true;
        else if (obj == null || !(obj instanceof Coordinate))
            result = false;
        else
            result = isEqual((Coordinate) obj);

        assertClassInvariants();
        return result;
    }

    @Override
    public int hashCode() {
        assertClassInvariants();

        int result = asCartesianCoordinate().hashCode();

        assertClassInvariants();
        return result;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        assertConversionImplemented();

        CartesianCoordinate result = asSphericalCoordinate().asCartesianCoordinate();

        assertClassInvariants();
        return result;
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        assertClassInvariants();
        assertConversionImplemented();

        SphericalCoordinate result = asCartesianCoordinate().asSphericalCoordinate();

        assertClassInvariants();
        return result;
    }

    protected void assertClassInvariants() {
    }

    protected void assertConversionImplemented() {
        try {
            if (!getClass().getMethod("asCartesianCoordinate").getDeclaringClass().equals(getClass())
                    && !getClass().getMethod("asSphericalCoordinate").getDeclaringClass().equals(getClass()))
                throw new NoSuchMethodException(
                        "neither asCartesianCoordinate nor asSphericalCoordinate is implemented");
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    protected void assertNotNullArgument(Object obj) {
        if (obj == null)
            throw new NullPointerException("The provided argument was null");
    }

    protected void assertResultNotNan(double value) {
        if (Double.isNaN(value))
            throw new RuntimeException("the result is Nan");
    }
}
