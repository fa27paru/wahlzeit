package org.wahlzeit.model.location;

public class Location {
    private Coordinate coordinate;

    public static final Location DEFAULT = getFromString("");

    public static Location getFromString(String strLocation) throws IllegalArgumentException {
        try {
            return new Location(Coordinate.getFromString(strLocation));
        } catch (Exception cause) {
            throw new IllegalArgumentException("invalid Location string: " + strLocation, cause);
        }
    }

    public Location(Coordinate coordinate) {
        if (coordinate == null)
            throw new IllegalArgumentException("Location must have a non null coordinate");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate newCoordinate) {
        this.coordinate = newCoordinate;
    }

    @Override
    public String toString() {
        return coordinate.toString();
    }
}
