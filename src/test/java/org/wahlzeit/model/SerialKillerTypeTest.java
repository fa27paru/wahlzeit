package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class SerialKillerTypeTest {
    @Test
    public void testEquals() {
        HashMap<String, String> attributes = new HashMap<>();
        SerialKillerType type1 = SerialKillerType.getSerialKillerType("type1", attributes, null);
        SerialKillerType type2 = SerialKillerType.getSerialKillerType("type2", new HashMap<>(), null);
        SerialKillerType type1Clone = SerialKillerType.getSerialKillerType("type1", attributes, null);

        boolean isEqual1To1 = type1.equals(type1);
        boolean isEqual1To2 = type1.equals(type2);
        boolean isEqual1To3 = type1.equals(type1Clone);
        boolean isEqual1ToNull = type1.equals(null);

        assertTrue(isEqual1To1);
        assertFalse(isEqual1To2);
        assertTrue(isEqual1To3);
        assertFalse(isEqual1ToNull);
    }

    @Test
    public void testSubtype() {
        SerialKillerType type1 = SerialKillerType.getSerialKillerType("type1", new HashMap<>(), null);
        SerialKillerType type2 = SerialKillerType.getSerialKillerType("type1", new HashMap<>(), type1);
        SerialKillerType type3 = SerialKillerType.getSerialKillerType("type1", new HashMap<>(), type2);
        SerialKillerType type4 = SerialKillerType.getSerialKillerType("type1", new HashMap<>(), null);

        assertTrue(type1.isSubType(null));
        assertTrue(type2.isSubType(type1));
        assertTrue(type3.isSubType(type1));
        assertFalse(type4.isSubType(type1));
    }
}
