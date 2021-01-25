package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import org.wahlzeit.utils.conditions.Preconditions;

public class SerialKillerManager {
    protected static SerialKillerManager instance = new SerialKillerManager();

    public SerialKillerType getSerialKillerType(ResultSet rset, String baseName) {
        Preconditions.assertNotNullArgument(rset);
        Preconditions.assertNotNullArgument(baseName);

        try {
            SerialKillerType superType = null;
            String superTypeBaseName = rset.getString(baseName + "superType");
            if(superTypeBaseName != null && !superTypeBaseName.isEmpty()) superType = getSerialKillerType(rset, superTypeBaseName);
            ResultSetMetaData rsmd = rset.getMetaData();
            int columnCount = rsmd.getColumnCount();
            HashMap<String, String> attributes = new HashMap<>();
            for(int i = 1; i <= columnCount; ++i)
            {
                String columnName = rsmd.getColumnName(i);
                if(columnName != null && columnName.startsWith(baseName + "_")) {
                    attributes.put(columnName, rset.getString(columnName));
                }
            }
            return SerialKillerType.getSerialKillerType(baseName, attributes, superType);
        } catch (SQLException e) {
            throw new RuntimeException("Could not extract serial killer type information");
        }
    }

    public SerialKiller createSerialKiller(ResultSet rset) {
        Preconditions.assertNotNullArgument(rset);

        try {
            SerialKillerType serialKillerType = getSerialKillerType(rset, rset.getString("serial_killer_type"));
            String serialKillerName = rset.getString("serial_killer_name");
            int provenVictims = rset.getInt("serial_killer_proven_victims");
            int possibleVictims = rset.getInt("serial_killer_possible_victims");
            SerialKiller result = new SerialKiller(serialKillerType);
            result.possibleVictims = possibleVictims;
            result.provenVictims = provenVictims;
            result.serialKillerName = serialKillerName;
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Could not extract serial killer information: " + e.getMessage());
        }
    }

    public void saveSerialKillerType(ResultSet rset, SerialKillerType serialKillerType) {
        Preconditions.assertNotNullArgument(rset);
        Preconditions.assertNotNullArgument(serialKillerType);
        try {
            if(serialKillerType.superType != null) saveSerialKillerType(rset, serialKillerType.superType);
            if(serialKillerType.superType != null) rset.updateString(serialKillerType.name + "superType", serialKillerType.superType.name);
            else rset.updateString(serialKillerType.name + "superType", "");
            for (HashMap.Entry<String,String> attribute : serialKillerType.attributes.entrySet()) {
                rset.updateString(serialKillerType.name + "_" + attribute.getKey(), attribute.getValue());                
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not save serial killer information");
        }
    }


    public void saveSerialKiller(ResultSet rset, SerialKiller serialKiller) {
        Preconditions.assertNotNullArgument(rset);
        Preconditions.assertNotNullArgument(serialKiller);
        try {
            saveSerialKillerType(rset, serialKiller.serialKillerType);
            rset.updateString("serial_killer_type", serialKiller.serialKillerType.name);
            rset.updateString("serial_killer_name", serialKiller.serialKillerName);
            rset.updateInt("serial_killer_proven_victims", serialKiller.provenVictims);
            rset.updateInt("serial_killer_possible_victims", serialKiller.possibleVictims);
        } catch (SQLException e) {
            throw new RuntimeException("Could not save serial killer information");
        }
    }

    protected SerialKillerManager() {}
    public static SerialKillerManager getInstance() {
        return instance;
    }
}
