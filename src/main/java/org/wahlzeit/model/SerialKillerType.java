package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Objects;

import org.wahlzeit.utils.conditions.Preconditions;


public class SerialKillerType {
    protected SerialKillerType superType = null;
    protected String name;
    protected HashMap<String, String> attributes;
    protected static HashMap<Integer, SerialKillerType> sharedSerialKillerTypes = new HashMap<>();

    public boolean isSubType(SerialKillerType superType) {
        if (this.superType == superType)
            return true;
        if (this.superType == null)
            return false;
        return this.superType.isSubType(superType);
    }

    public static SerialKillerType getSerialKillerType(String name, HashMap<String, String> attributes, SerialKillerType superType) {
        SerialKillerType newSerialKillerType = new SerialKillerType(name, attributes, superType);
        SerialKillerType result = sharedSerialKillerTypes.get(newSerialKillerType.hashCode());
        if (result == null) {
            synchronized (sharedSerialKillerTypes) {
                result = sharedSerialKillerTypes.get(newSerialKillerType.hashCode());
                if (result == null) {
                    sharedSerialKillerTypes.put(newSerialKillerType.hashCode(), newSerialKillerType);
                    result = newSerialKillerType;
                }
            }
        }
        return result;
    }

    protected SerialKillerType(String name, HashMap<String, String> attributes, SerialKillerType superType) {
        Preconditions.assertNotNullArgument(name);
        Preconditions.assertNotNullArgument(attributes);

        this.name = name;
        this.attributes = attributes;
        this.superType = superType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, superType, attributes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (obj == null || !(obj instanceof SerialKillerType))
            return false;
        return obj.hashCode() == hashCode();
    }
}
