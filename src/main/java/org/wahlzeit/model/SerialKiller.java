package org.wahlzeit.model;

import org.wahlzeit.utils.conditions.Preconditions;

public class SerialKiller {
    SerialKillerType serialKillerType;
    String serialKillerName = "unknown";
    int provenVictims = 0;
    int possibleVictims = 0;

    public SerialKiller(SerialKillerType serialKillerType) {
        Preconditions.assertNotNullArgument(serialKillerType);

        this.serialKillerType = serialKillerType;

        assertClassInvariants();
    }

    protected void assertClassInvariants() {
        if (serialKillerName == null)
            throw new IllegalStateException("the serial killer name must not be null");
        if (Double.isNaN(provenVictims) || Double.isNaN(possibleVictims))
            throw new IllegalStateException("the serial killer victims must not be Nan");
        if (provenVictims < 0 || possibleVictims < 0)
            throw new IllegalStateException("the serial killer victims must not be negative");
    }
}
