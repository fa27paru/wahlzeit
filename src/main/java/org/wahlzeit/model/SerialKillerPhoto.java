package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialKillerPhoto extends Photo {
    protected String serialKillerName;
    protected int provenVictims;
    protected int possibleVictims;

    public SerialKillerPhoto() {
        super();
    }

    public SerialKillerPhoto(PhotoId id) {
        super(id);
    }

    public SerialKillerPhoto(ResultSet rs) throws SQLException {
        super(rs);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        serialKillerName = rset.getString("serial_killer_name");
        provenVictims = rset.getInt("serial_killer_proven_victims");
        possibleVictims = rset.getInt("serial_killer_possible_victims");
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("serial_killer_name", serialKillerName);
        rset.updateInt("serial_killer_proven_victims", provenVictims);
        rset.updateInt("serial_killer_possible_victims", possibleVictims);
    }

    public String getSerialKillerName() {
        return serialKillerName;
    }

    public void setSerialKillerName(String serialKillerName) {
        this.serialKillerName = serialKillerName;
        incWriteCount();
    }

    public int getProvenVictims() {
        return provenVictims;
    }

    public void setProvenVictims(int provenVictims) {
        this.provenVictims = provenVictims;
        incWriteCount();
    }

    public int getPossibleVictims() {
        return possibleVictims;
    }

    public void setPossibleVictims(int possibleVictims) {
        this.possibleVictims = possibleVictims;
        incWriteCount();
    }
}
