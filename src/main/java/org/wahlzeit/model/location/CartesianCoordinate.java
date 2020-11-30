package org.wahlzeit.model.location;

import java.util.Objects;

public class CartesianCoordinate implements Coordinate {
    private final static double EPSILON = 0.0001;

    public double x;
    public double y;
    public double z;

    public static CartesianCoordinate getFromString(String strCoordinate) throws IllegalArgumentException {
        if (strCoordinate == null || strCoordinate.trim().isEmpty())
            return new CartesianCoordinate();

        try {
            String[] strCoords = strCoordinate.split(";");
            return new CartesianCoordinate(Double.parseDouble(strCoords[0]), Double.parseDouble(strCoords[1]),
                    Double.parseDouble(strCoords[2]));
        } catch (Exception cause) {
            throw new IllegalArgumentException("invalid Coordinate string: " + strCoordinate, cause);
        }
    }

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CartesianCoordinate() {
        this(0, 0, 0);
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        double xDiff = otherCartesian.x - x;
        double yDiff = otherCartesian.y - y;
        double zDiff = otherCartesian.z - z;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }

    public boolean isEqual(Coordinate other) {
        return getCartesianDistance(other) <= EPSILON;
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
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return Double.toString(x) + ";" + Double.toString(y) + ";" + Double.toString(z) + ";";
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        double r = Math.sqrt(x * x + y * y + z * z);
        double theta = Math.atan2(Math.sqrt(x * x + y * y), z);
        double phi = Math.atan2(y, x);        
        return new SphericalCoordinate(phi, theta, r);
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        return asSphericalCoordinate().getCentralAngle(other);
    }
}
