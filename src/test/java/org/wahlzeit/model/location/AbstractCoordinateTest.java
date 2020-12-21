package org.wahlzeit.model.location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class AbstractCoordinateTest extends AbstractCoordinate {
    @Test(expected = UnsupportedOperationException.class)
    public void testAsCartesianCoordinateNoImplementation() {
        Coordinate coord = new AbstractCoordinateTest();
        coord.asCartesianCoordinate();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsSphericalCoordinateNoImplementation() {
        Coordinate coord = new AbstractCoordinateTest();
        coord.asSphericalCoordinate();
    }

    @Test
    public void testAsCartesianCoordinate() {
        SphericalCoordinate mockSphericalCoord = mock(SphericalCoordinate.class);
        CartesianCoordinate mockCartesianCoord = mock(CartesianCoordinate.class);
        when(mockSphericalCoord.asCartesianCoordinate()).thenReturn(mockCartesianCoord);
        class DummyAbstractCoord extends AbstractCoordinate {
            @Override
            public SphericalCoordinate asSphericalCoordinate() {
                return mockSphericalCoord;
            }
        }
        Coordinate coord = new DummyAbstractCoord();

        Coordinate result = coord.asCartesianCoordinate();

        assertSame(mockCartesianCoord, result);
    }

    @Test
    public void testAsSphericalCoordinate() {
        SphericalCoordinate mockSphericalCoord = mock(SphericalCoordinate.class);
        CartesianCoordinate mockCartesianCoord = mock(CartesianCoordinate.class);
        when(mockCartesianCoord.asSphericalCoordinate()).thenReturn(mockSphericalCoord);
        class DummyAbstractCoord extends AbstractCoordinate {
            @Override
            public CartesianCoordinate asCartesianCoordinate() {
                return mockCartesianCoord;
            }
        }
        Coordinate coord = new DummyAbstractCoord();

        Coordinate result = coord.asSphericalCoordinate();

        assertSame(mockSphericalCoord, result);
    }

    @Test
    public void testEqualsWithNull() {
        Coordinate coord = new AbstractCoordinateTest();
        assertFalse(coord.equals(null));
    }

    @Test
    public void testEqualsWithSameClass() {
        Coordinate coord = new AbstractCoordinateTest();
        assertTrue(coord.equals(coord));
    }

    @Test
    public void testEqualsWithNoCoordinateClass() {
        Coordinate coord = new AbstractCoordinateTest();
        assertFalse(coord.equals(new Object()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistanceNullArgument() {
        Coordinate coord = new AbstractCoordinateTest();
        coord.getCartesianDistance(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngleNullArgument() {
        Coordinate coord = new AbstractCoordinateTest();
        coord.getCentralAngle(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsEqualNullArgument() {
        Coordinate coord = new AbstractCoordinateTest();
        coord.isEqual(null);
    }
}
