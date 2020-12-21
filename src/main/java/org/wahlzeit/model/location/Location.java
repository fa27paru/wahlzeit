package org.wahlzeit.model.location;

import org.wahlzeit.utils.conditions.Preconditions;

public class Location {
    private Coordinate coordinate;

    public static final Location DEFAULT = getFromString("");

    public static Location getFromString(String strLocation) {
        try {
            return new Location(CartesianCoordinate.getFromString(strLocation));
        } catch (Exception cause) {
            throw new IllegalArgumentException("invalid Location string: " + strLocation, cause);
        }
    }

    public Location(Coordinate coordinate) {
        Preconditions.assertNotNullArgument(coordinate, "location must have a non null coordinate");
        
        this.coordinate = coordinate;

        assertClassInvariants();
    }

    public Coordinate getCoordinate() {
        assertClassInvariants();

        return coordinate;
    }

    public void setCoordinate(Coordinate newCoordinate) {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(newCoordinate);

        this.coordinate = newCoordinate;
    }

    @Override
    public String toString() {
        assertClassInvariants();

        return coordinate.asCartesianCoordinate().toString();
    }

    protected void assertClassInvariants() {
        if(coordinate == null) throw new IllegalStateException("the coordinate must not be null");
    }
}
