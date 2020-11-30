package org.wahlzeit.model.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SphericalCoordinateTest {
    @Test
    public void testDefaultCoordinates() {
        Coordinate expected = new SphericalCoordinate(0, 0, 0);

        Coordinate actual = new SphericalCoordinate();

        assertTrue("The default coordinates should be 0, 0, 0", expected.equals(actual));
    }

    @Test
    public void testConversionToCartesian() {
        Coordinate coord1 = new SphericalCoordinate(0, 0, 0);
        Coordinate coord2 = new SphericalCoordinate(1, 2, 3.2);
        Coordinate coord3 = new SphericalCoordinate(1, 2, 3.2);

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
    }

    @Test
    public void testGetCentralAngle() {
        Coordinate coord1 = new SphericalCoordinate(0, 0, 0);
        Coordinate coord2 = new SphericalCoordinate(1, 2, 3.2);
        Coordinate coord3 = new SphericalCoordinate(Math.PI, 0, 3.2);

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
        assertEquals(Math.PI, angle13, 0.001);
        assertEquals(1.79758, angle21, 0.001);
        assertEquals(0.0, angle22, 0.001);
        assertEquals(1.3440, angle23, 0.001);
        assertEquals(Math.PI, angle31, 0.001);
        assertEquals(1.3440, angle32, 0.001);
        assertEquals(0.0, angle33, 0.001);
    }
}
