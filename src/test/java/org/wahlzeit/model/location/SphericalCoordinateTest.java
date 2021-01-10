package org.wahlzeit.model.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SphericalCoordinateTest {
    @Test
    public void testDefaultCoordinates() {
        Coordinate expected = SphericalCoordinate.getSphericalCoordinate(0, 0, 0);

        Coordinate actual = SphericalCoordinate.getSphericalCoordinate();

        assertTrue("The default coordinates should be 0, 0, 0", expected.equals(actual));
    }

    @Test
    public void testSameInstance() {
        Coordinate coord1 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3);
        Coordinate coord2 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3);
        Coordinate coord3 = SphericalCoordinate.getSphericalCoordinate(1, 2.2, 3);

        assertTrue(coord1 == coord2);
        assertFalse(coord1 == coord3);
    }

    @Test
    public void testRadiusSetter() {
        SphericalCoordinate coord1 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3);

        SphericalCoordinate coord2 = coord1.setRadius(5);
        SphericalCoordinate coord3 = coord2.setRadius(3);

        assertFalse(coord1 == coord2);
        assertTrue(coord1 == coord3);
        assertEquals(3, coord1.getRadius(), 0.00001);
        assertEquals(5, coord2.getRadius(), 0.00001);
    }

    @Test
    public void testConversionToCartesian() {
        Coordinate coord1 = SphericalCoordinate.getSphericalCoordinate(0, 0, 0);
        Coordinate coord2 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3.2);
        Coordinate coord3 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3.2);

        Coordinate sCoord1 = coord1.asCartesianCoordinate();
        Coordinate sCoord2 = coord2.asCartesianCoordinate();
        Coordinate sCoord3 = coord3.asCartesianCoordinate().asSphericalCoordinate();

        assertEquals(coord1, sCoord1);
        assertEquals(coord2, sCoord2);
        assertEquals(coord3, sCoord3);
        assertEquals(sCoord2, sCoord3);
        assertEquals(coord2, coord3);
        assertNotEquals(coord1, coord2);
        assertNotEquals(sCoord1, sCoord2);
        assertTrue(coord3 == sCoord3);
    }

    @Test
    public void testGetCentralAngle() {
        Coordinate coord1 = SphericalCoordinate.getSphericalCoordinate(0, 0, 0);
        Coordinate coord2 = SphericalCoordinate.getSphericalCoordinate(1, 2, 3.2);
        Coordinate coord3 = SphericalCoordinate.getSphericalCoordinate(Math.PI - 0.001, 0, 3.2);

        double angle11 = coord1.getCentralAngle(coord1);
        double angle12 = coord1.getCentralAngle(coord2);
        double angle13 = coord1.getCentralAngle(coord3);
        double angle21 = coord2.getCentralAngle(coord1);
        double angle22 = coord2.getCentralAngle(coord2);
        double angle23 = coord2.getCentralAngle(coord3);
        double angle31 = coord3.getCentralAngle(coord1);
        double angle32 = coord3.getCentralAngle(coord2);
        double angle33 = coord3.getCentralAngle(coord3);

        assertEquals(0.0, angle11, 0.001);
        assertEquals(1.79758, angle12, 0.001);
        assertEquals(Math.PI - 0.001, angle13, 0.001);
        assertEquals(1.79758, angle21, 0.001);
        assertEquals(0.0, angle22, 0.001);
        assertEquals(1.3440, angle23, 0.001);
        assertEquals(Math.PI - 0.001, angle31, 0.001);
        assertEquals(1.3440, angle32, 0.001);
        assertEquals(0.0, angle33, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNanValue() {
        SphericalCoordinate.getSphericalCoordinate(0, Double.NaN, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeAngle() {
        SphericalCoordinate.getSphericalCoordinate(0, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithPiAsAngle() {
        SphericalCoordinate.getSphericalCoordinate(Math.PI, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceNullArgument() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.getCentralAngle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsEqualNullArgument() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.isEqual(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhiNanArgument() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setPhi(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThetaNanArgument() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setTheta(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusNanArgument() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setRadius(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhiNegativeAngle() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setPhi(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThetaNegativeAngle() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setTheta(-1);
    }

     @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusNegative() {
        SphericalCoordinate coord = SphericalCoordinate.getSphericalCoordinate();

        coord.setRadius(-1);
    }
}
