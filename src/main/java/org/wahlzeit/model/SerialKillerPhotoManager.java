package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialKillerPhotoManager extends PhotoManager {
    public SerialKillerPhotoManager() {
        photoTagCollector = SerialKillerPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    protected Photo createObject(ResultSet rset) throws SQLException {
        return SerialKillerPhotoFactory.getInstance().createPhoto(rset);
    }
}
