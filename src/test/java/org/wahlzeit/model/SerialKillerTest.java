package org.wahlzeit.model;

import org.junit.Test;

public class SerialKillerTest {
    @Test(expected = IllegalArgumentException.class)
    public void testNullSuperType() {
        SerialKiller serialKiller = new SerialKiller(null);
    }
}
