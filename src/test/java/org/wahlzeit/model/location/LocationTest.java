package org.wahlzeit.model.location;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class LocationTest {
    @Test
    public void testLocationConstructor() {
        Coordinate mockCoord = mock(Coordinate.class);

        Location loc = new Location(mockCoord);

        assertSame(mockCoord, loc.getCoordinate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLocationConstructorWithNullCoordinate() {
        new Location(null);
    }

    @Test
    public void testGetFromString() {
        Coordinate expectedCoord = new CartesianCoordinate(1.0, 2.2, 3.2);

        Location loc = Location.getFromString("1.0;2.2;3.2;");
        Coordinate actualCoordinate = loc.getCoordinate();

        assertTrue(
                "Location.getFromString(\"1.0;2.2;3.2;\") should return a Location with the Coordinates 1.0, 2.2, 3.2",
                expectedCoord.equals(actualCoordinate));
    }

    @Test
    public void testGetFromStringWithEmptyString() {
        Coordinate expectedCoord = new CartesianCoordinate();

        Location loc = Location.getFromString("");
        Coordinate actualCoordinate = loc.getCoordinate();

        assertTrue("Location.getFromString(\"\") should return a Location with the default Coordinates",
                expectedCoord.equals(actualCoordinate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFromStringWithInvalidString() {
        Location.getFromString("23.0;23.1;abc");
    }
}
