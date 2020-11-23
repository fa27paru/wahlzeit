package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {
    @Test
    public void testGetFromString() {
        Coordinate expectedCoord = new Coordinate(1.0, 2.2, 3.2);

        Coordinate actualCoordinate = Coordinate.getFromString("1.0;2.2;3.2;");

        assertTrue(
                "Coordinate.getFromString(\"1.0;2.2;3.2;\") should return the Coordinates 1.0, 2.2, 3.2",
                expectedCoord.equals(actualCoordinate));
    }

    @Test
    public void testGetFromStringWithEmptyString() {
        Coordinate expectedCoord = new Coordinate();

        Coordinate actualCoordinate = Coordinate.getFromString("");

        assertTrue("Coordinate.getFromString(\"\") should return the default Coordinates",
                expectedCoord.equals(actualCoordinate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFromStringWithInvalidString() {
        Coordinate.getFromString("23.0;23.1;abc");
    }

    @Test
    public void testDefaultCoordinates() {
        Coordinate expected = new Coordinate(0, 0, 0);

        Coordinate actual = new Coordinate();

        assertTrue("The default coordinates should be 0, 0, 0", expected.equals(actual));
    }

    @Test
    public void testGetDistance() {
        Coordinate startCoord = new Coordinate(0, 1, 0);
        Coordinate endCoord = new Coordinate(3, 4, 5);

        double dist1 = startCoord.getDistance(endCoord);
        double dist2 = endCoord.getDistance(startCoord);

        assertEquals(6.55743852, dist1, 0.00001);
        assertEquals(dist1, dist2, 0.00001);
    }

    @Test
    public void testIsEqual() {
        Coordinate coord1 = new Coordinate(1, 2, 3);
        Coordinate coord2 = new Coordinate(1, 2, 3);
        Coordinate coord3 = new Coordinate(1, 2.2, 3);

        boolean isEqual1To1 = coord1.equals(coord1);
        boolean isEqual1To2 = coord1.equals(coord2);
        boolean isEqual1To3 = coord1.equals(coord3);
        boolean isEqual1ToNull = coord1.equals(null);

        assertTrue(isEqual1To1);
        assertTrue(isEqual1To2);
        assertFalse(isEqual1To3);
        assertFalse(isEqual1ToNull);
    }

    @Test
    public void testHashCode() {
        Coordinate coord1 = new Coordinate(1, 2, 3);
        Coordinate coord2 = new Coordinate(1, 2, 3);
        Coordinate coord3 = new Coordinate(1, 2.2, 3);

        int hash1 = coord1.hashCode();
        int hash2 = coord2.hashCode();
        int hash3 = coord3.hashCode();

        assertEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
    }

    @Test
    public void testToString() {
        Coordinate coord1 = new Coordinate(0, 0, 0);
        Coordinate coord2 = new Coordinate(1, 2, 3.2);

        String str1 = coord1.toString();
        String str2 = coord2.toString();

        assertEquals("0.0;0.0;0.0;", str1);
        assertEquals("1.0;2.0;3.2;", str2);
    }
}
