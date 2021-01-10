package org.wahlzeit.model.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CartesianCoordinateTest {
    @Test
    public void testGetFromString() {
        Coordinate expectedCoord = CartesianCoordinate.getCartesianCoordinate(1.0, 2.2, 3.2);

        Coordinate actualCoordinate = CartesianCoordinate.getFromString("1.0;2.2;3.2;");

        assertTrue("Coordinate.getFromString(\"1.0;2.2;3.2;\") should return the Coordinates 1.0, 2.2, 3.2",
                expectedCoord.equals(actualCoordinate));
    }

    @Test
    public void testGetFromStringWithEmptyString() {
        Coordinate expectedCoord = CartesianCoordinate.getCartesianCoordinate();

        Coordinate actualCoordinate = CartesianCoordinate.getFromString("");

        assertTrue("Coordinate.getFromString(\"\") should return the default Coordinates",
                expectedCoord.equals(actualCoordinate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFromStringWithInvalidString() {
        CartesianCoordinate.getFromString("23.0;23.1;abc");
    }

    @Test
    public void testDefaultCoordinates() {
        Coordinate expected = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);

        Coordinate actual = CartesianCoordinate.getCartesianCoordinate();

        assertTrue("The default coordinates should be 0, 0, 0", expected.equals(actual));
    }

    @Test
    public void testGetCartesianDistance() {
        Coordinate startCoord = CartesianCoordinate.getCartesianCoordinate(0, 1, 0);
        Coordinate endCoord = CartesianCoordinate.getCartesianCoordinate(3, 4, 5);

        double dist1 = startCoord.getCartesianDistance(endCoord);
        double dist2 = endCoord.getCartesianDistance(startCoord);

        assertEquals(6.55743852, dist1, 0.00001);
        assertEquals(dist1, dist2, 0.00001);
    }

    @Test
    public void testIsEqual() {
        Coordinate coord1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord3 = CartesianCoordinate.getCartesianCoordinate(1, 2.2, 3);

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
        Coordinate coord1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord3 = CartesianCoordinate.getCartesianCoordinate(1, 2.2, 3);

        int hash1 = coord1.hashCode();
        int hash2 = coord2.hashCode();
        int hash3 = coord3.hashCode();

        assertEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);
    }

    @Test
    public void testSameInstance() {
        Coordinate coord1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        Coordinate coord3 = CartesianCoordinate.getCartesianCoordinate(1, 2.2, 3);

        assertTrue(coord1 == coord2);
        assertFalse(coord1 == coord3);
    }

    @Test
    public void testXSetter() {
        CartesianCoordinate coord1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);

        CartesianCoordinate coord2 = coord1.setX(5);
        CartesianCoordinate coord3 = coord2.setX(1);

        assertFalse(coord1 == coord2);
        assertTrue(coord1 == coord3);
        assertEquals(1, coord1.getX(), 0.00001);
        assertEquals(5, coord2.getX(), 0.00001);
    }

    @Test
    public void testToString() {
        Coordinate coord1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
        Coordinate coord2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3.2);

        String str1 = coord1.toString();
        String str2 = coord2.toString();

        assertEquals("0.0;0.0;0.0;", str1);
        assertEquals("1.0;2.0;3.2;", str2);
    }

    @Test
    public void testConversionToSpherical() {
        Coordinate coord1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
        Coordinate coord2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3.2);
        Coordinate coord3 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3.2);

        Coordinate sCoord1 = coord1.asSphericalCoordinate();
        Coordinate sCoord2 = coord2.asSphericalCoordinate();
        Coordinate sCoord3 = coord3.asSphericalCoordinate().asCartesianCoordinate();

        assertEquals(coord1, sCoord1);
        assertEquals(coord2, sCoord2);
        assertEquals(coord3, sCoord3);
        assertEquals(sCoord2, sCoord3);
        assertNotEquals(coord1, sCoord2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNanValue() {
        CartesianCoordinate.getCartesianCoordinate(0, Double.NaN, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceNullArgument() {
        CartesianCoordinate coord = CartesianCoordinate.getCartesianCoordinate();

        coord.getCartesianDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsEqualNullArgument() {
        CartesianCoordinate coord = CartesianCoordinate.getCartesianCoordinate();

        coord.isEqual(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetXNanArgument() {
        CartesianCoordinate coord = CartesianCoordinate.getCartesianCoordinate();

        coord.setX(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetYNanArgument() {
        CartesianCoordinate coord = CartesianCoordinate.getCartesianCoordinate();

        coord.setY(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetZNanArgument() {
        CartesianCoordinate coord = CartesianCoordinate.getCartesianCoordinate();

        coord.setZ(Double.NaN);
    }
}
