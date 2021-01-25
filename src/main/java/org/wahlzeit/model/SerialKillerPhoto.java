package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.wahlzeit.utils.PatternInstance;
import org.wahlzeit.utils.conditions.Preconditions;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteProduct"
	}
)
public class SerialKillerPhoto extends Photo {
    protected SerialKiller serialKiller = new SerialKiller(SerialKillerType.getSerialKillerType("SerialKillerType", new HashMap<>(), null));;

    public SerialKillerPhoto() {
        super();

        assertClassInvariants();
    }

    public SerialKillerPhoto(PhotoId id) {
        super(id);

        assertClassInvariants();
    }

    public SerialKillerPhoto(ResultSet rs) throws SQLException {
        super(rs);

        assertClassInvariants();
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        Preconditions.assertNotNullArgument(rset);

        super.readFrom(rset);
        serialKiller = SerialKillerManager.getInstance().createSerialKiller(rset);

        assertClassInvariants();
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(rset);

        super.writeOn(rset);
        SerialKillerManager.getInstance().saveSerialKiller(rset, serialKiller);
    }

    public String getSerialKillerName() {
        assertClassInvariants();
        return serialKiller.serialKillerName;
    }

    public void setSerialKillerName(String serialKillerName) {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(serialKillerName);

        this.serialKiller.serialKillerName = serialKillerName;
        incWriteCount();
    }

    public int getProvenVictims() {
        assertClassInvariants();
        return serialKiller.provenVictims;
    }

    public void setProvenVictims(int provenVictims) {
        assertClassInvariants();
        Preconditions.assertArgumentNotNan(provenVictims);
        Preconditions.assertNonNegativeArgument(provenVictims);

        this.serialKiller.provenVictims = provenVictims;
        incWriteCount();
    }

    public int getPossibleVictims() {
        assertClassInvariants();
        return serialKiller.possibleVictims;
    }

    public void setPossibleVictims(int possibleVictims) {
        assertClassInvariants();
        Preconditions.assertArgumentNotNan(possibleVictims);
        Preconditions.assertNonNegativeArgument(possibleVictims);

        this.serialKiller.possibleVictims = possibleVictims;
        incWriteCount();
    }

    protected void assertClassInvariants() {
        if(serialKiller == null)
            throw new IllegalStateException("the serial killer must not be null");
    }
}
