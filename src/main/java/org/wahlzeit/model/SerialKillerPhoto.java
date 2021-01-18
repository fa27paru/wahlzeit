package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.PatternInstance;
import org.wahlzeit.utils.conditions.Preconditions;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteProduct"
	}
)
public class SerialKillerPhoto extends Photo {
    protected String serialKillerName = "unknown";
    protected int provenVictims = 0;
    protected int possibleVictims = 0;

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
        try {
            serialKillerName = rset.getString("serial_killer_name");
        } catch (SQLException e) {
            SysLog.logSysError("Could not get serial killer name in database");
        }
        try {
            provenVictims = rset.getInt("serial_killer_proven_victims");
            possibleVictims = rset.getInt("serial_killer_possible_victims");
        } catch (SQLException e) {
            SysLog.logSysError("Could not get serial killer victims from database");
        }

        assertClassInvariants();
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertClassInvariants();
        Preconditions.assertNotNullArgument(rset);

        super.writeOn(rset);
        try {
            rset.updateString("serial_killer_name", serialKillerName);
            rset.updateInt("serial_killer_proven_victims", provenVictims);
            rset.updateInt("serial_killer_possible_victims", possibleVictims);
        } catch (SQLException e) {
            SysLog.logSysError("Could not save serial killer information");
        }
    }

    public String getSerialKillerName() {
        return serialKillerName;
    }

    public void setSerialKillerName(String serialKillerName) {
        Preconditions.assertNotNullArgument(serialKillerName);

        this.serialKillerName = serialKillerName;
        incWriteCount();
    }

    public int getProvenVictims() {
        return provenVictims;
    }

    public void setProvenVictims(int provenVictims) {
        Preconditions.assertArgumentNotNan(provenVictims);
        Preconditions.assertNonNegativeArgument(provenVictims);

        this.provenVictims = provenVictims;
        incWriteCount();
    }

    public int getPossibleVictims() {
        return possibleVictims;
    }

    public void setPossibleVictims(int possibleVictims) {
        Preconditions.assertArgumentNotNan(possibleVictims);
        Preconditions.assertNonNegativeArgument(possibleVictims);

        this.possibleVictims = possibleVictims;
        incWriteCount();
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
