package org.wahlzeit.model.location;

import java.util.HashMap;
import java.util.Objects;

import org.wahlzeit.utils.conditions.Preconditions;
import org.wahlzeit.utils.conditions.Postconditions;

public class CartesianCoordinate extends AbstractCoordinate {
    private final static double EPSILON = 0.0001;

    protected static HashMap<Integer, CartesianCoordinate> sharedCartesianCoordinates = new HashMap<>();

    protected final double x;
    protected final double y;
    protected final double z;

    public static CartesianCoordinate getFromString(String strCoordinate) throws IllegalArgumentException {
        if (strCoordinate == null || strCoordinate.trim().isEmpty())
            return getCartesianCoordinate();

        try {
            String[] strCoords = strCoordinate.split(";");
            return getCartesianCoordinate(Double.parseDouble(strCoords[0]), Double.parseDouble(strCoords[1]),
                    Double.parseDouble(strCoords[2]));
        } catch (Exception cause) {
            throw new IllegalArgumentException("invalid Coordinate string: " + strCoordinate, cause);
        }
    }

    public static CartesianCoordinate getCartesianCoordinate() {
        return getCartesianCoordinate(0, 0, 0);
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
        CartesianCoordinate newCoordinate = new CartesianCoordinate(x, y, z);
        CartesianCoordinate result = sharedCartesianCoordinates.get(newCoordinate.hashCode());
        if (result == null) {
            synchronized (sharedCartesianCoordinates) {
                result = sharedCartesianCoordinates.get(newCoordinate.hashCode());
                if (result == null) {
                    sharedCartesianCoordinates.put(newCoordinate.hashCode(), newCoordinate);
                    result = newCoordinate;
                }
            }
        }
        return result;
    }

    protected CartesianCoordinate(double x, double y, double z) {
        Preconditions.assertArgumentNotNan(x);
        Preconditions.assertArgumentNotNan(y);
        Preconditions.assertArgumentNotNan(z);

        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(other);

        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        double xDiff = otherCartesian.x - x;
        double yDiff = otherCartesian.y - y;
        double zDiff = otherCartesian.z - z;
        double result = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);

        Postconditions.assertResultNotNan(result);
        Postconditions.assertNonNegativeResult(result);
        return result;
    }

    public boolean isEqual(Coordinate other) {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(other);

        return getCartesianDistance(other) <= EPSILON;
    }

    @Override
    public int hashCode() {
        assertClassInvariants();

        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        assertClassInvariants();

        return Double.toString(x) + ";" + Double.toString(y) + ";" + Double.toString(z) + ";";
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();

        return this;
    }

    @Override
    public SphericalCoordinate asSphericalCoordinate() {
        assertClassInvariants();

        double r = Math.sqrt(x * x + y * y + z * z);
        double theta = Math.atan2(Math.sqrt(x * x + y * y), z);
        double phi = Math.atan2(y, x);
        SphericalCoordinate result = SphericalCoordinate.getSphericalCoordinate(phi, theta, r);

        return result;
    }

    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z))
            throw new IllegalStateException("cartesian coordinates can not be Nan");
        super.assertClassInvariants();
    }

    public double getX() {
        assertClassInvariants();

        return x;
    }

    public CartesianCoordinate setX(double x) {
        Preconditions.assertArgumentNotNan(x);

        return getCartesianCoordinate(x, y, z);
    }

    public double getY() {
        assertClassInvariants();

        return y;
    }

    public CartesianCoordinate setY(double y) {
        Preconditions.assertArgumentNotNan(y);

        return getCartesianCoordinate(x, y, z);
    }

    public double getZ() {
        assertClassInvariants();

        return z;
    }

    public CartesianCoordinate setZ(double z) {
        Preconditions.assertArgumentNotNan(z);

        return getCartesianCoordinate(x, y, z);
    }
}
