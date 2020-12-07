package org.wahlzeit.model.location;

public abstract class AbstractCoordinate implements Coordinate {
    @Override
    public double getCartesianDistance(Coordinate other) {
        return asCartesianCoordinate().getCartesianDistance(other);
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        return asSphericalCoordinate().getCentralAngle(other);
    }

    @Override
    public boolean isEqual(Coordinate other) {
        return asCartesianCoordinate().isEqual(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Coordinate))
            return false;
        return isEqual((Coordinate) obj);
    }

    @Override
    public int hashCode() {
        return asCartesianCoordinate().hashCode();
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        try {
            if (!getClass().getMethod("asSphericalCoordinate").getDeclaringClass().equals(getClass()))
                throw new Exception("neither asCartesianCoordinate nor asSphericalCoordinate is not implemented");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return asSphericalCoordinate().asCartesianCoordinate();
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        try {
            if (!getClass().getMethod("asCartesianCoordinate").getDeclaringClass().equals(getClass()))
                throw new Exception("neither asCartesianCoordinate nor asSphericalCoordinate is not implemented");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return asCartesianCoordinate().asSphericalCoordinate();
    }
}
