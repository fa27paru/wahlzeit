package org.wahlzeit.model.location;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate();
    double getCartesianDistance(Coordinate other);
    SphericalCoordinate asSphericalCoordinate();
    double getCentralAngle(Coordinate other);
    boolean isEqual(Coordinate other);
}
