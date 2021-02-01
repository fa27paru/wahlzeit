package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class SerialKillerManagerTest {
    @Test
    public void testReadFromRSet() throws SQLException {
        var rset = Mockito.mock(ResultSet.class);
        var rsmd = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(rset.getString("serial_killer_type")).thenReturn("SerialKillerType");
        Mockito.when(rset.getString("SerialKillerType_superType")).thenReturn("");
        Mockito.when(rset.getMetaData()).thenReturn(rsmd);
        Mockito.when(rsmd.getColumnCount()).thenReturn(0);
        Mockito.when(rset.getString("serial_killer_name")).thenReturn("Example Name");
        Mockito.when(rset.getInt("serial_killer_proven_victims")).thenReturn(3);
        Mockito.when(rset.getInt("serial_killer_possible_victims")).thenReturn(4);

        var serialKiller = SerialKillerManager.getInstance().createSerialKiller(rset);

        assertEquals(serialKiller.serialKillerName, "Example Name");
        assertEquals(serialKiller.provenVictims, 3);
        assertEquals(serialKiller.possibleVictims, 4);
        assertNotNull(serialKiller.serialKillerType);
        assertEquals(serialKiller.serialKillerType.name, "SerialKillerType");
        assertNull(serialKiller.serialKillerType.superType);
    }

    @Test
    public void testWriteToRSet() throws SQLException {
        var rset = Mockito.mock(ResultSet.class);
        var serialKiller = SerialKillerManager.getInstance().createSerialKiller();
        serialKiller.serialKillerName = "Example Name";
        serialKiller.provenVictims = 3;
        serialKiller.possibleVictims = 4;
        
        SerialKillerManager.getInstance().saveSerialKiller(rset, serialKiller);

        Mockito.verify(rset).updateString("SerialKillerType_superType", "");
        Mockito.verify(rset).updateString("serial_killer_type", "SerialKillerType");
        Mockito.verify(rset).updateString("serial_killer_name", "Example Name");
        Mockito.verify(rset).updateInt("serial_killer_proven_victims", 3);
        Mockito.verify(rset).updateInt("serial_killer_possible_victims", 4);
    }
}
