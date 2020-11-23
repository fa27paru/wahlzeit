package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SerialKillerPhotoTest {
    @Test
    public void testSetName() {
        SerialKillerPhoto photo = new SerialKillerPhoto();
        String newName = "Max";

        photo.setSerialKillerName(newName);

        assertEquals(newName, photo.getSerialKillerName());
        assertTrue(photo.isDirty());
    }

    @Test
    public void testProvenVictims() {
        SerialKillerPhoto photo = new SerialKillerPhoto();
        int newVictims = 1234;

        photo.setProvenVictims(newVictims);

        assertEquals(newVictims, photo.getProvenVictims());
        assertTrue(photo.isDirty());
    }

    @Test
    public void testPossibleVictims() {
        SerialKillerPhoto photo = new SerialKillerPhoto();
        int newVictims = 1234;

        photo.setPossibleVictims(newVictims);

        assertEquals(newVictims, photo.getPossibleVictims());
        assertTrue(photo.isDirty());
    }
}
