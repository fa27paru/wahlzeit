package org.wahlzeit.model;

public class Coordinate {
    private final static double EPSILON = 0.0001;

    public double x;
    public double y;
    public double z;

    public static Coordinate getFromString(String strCoordinate) throws IllegalArgumentException {
        if (strCoordinate == null || strCoordinate.trim().isEmpty())
            return new Coordinate();

        try {
            String[] strCoords = strCoordinate.split(";");
            return new Coordinate(Double.parseDouble(strCoords[0]), Double.parseDouble(strCoords[1]),
                    Double.parseDouble(strCoords[2]));
        } catch (Exception cause) {
            throw new IllegalArgumentException("invalid Coordinate string: " + strCoordinate, cause);
        }
    }

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate() {
        this(0, 0, 0);
    }

    public double getDistance(Coordinate other) {
        double xDiff = other.x - x;
        double yDiff = other.y - y;
        double zDiff = other.z - z;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }

    public boolean isEqual(Coordinate other) {
        if (getDistance(other) <= EPSILON)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        return isEqual((Coordinate) obj);
    }

    @Override
    public String toString() {
        return Double.toString(x) + ";" + Double.toString(y) + ";" + Double.toString(z) + ";";
    }
}
