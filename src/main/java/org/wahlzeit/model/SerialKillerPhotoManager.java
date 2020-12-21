package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.conditions.Preconditions;

public class SerialKillerPhotoManager extends PhotoManager {
    public SerialKillerPhotoManager() {
        photoTagCollector = SerialKillerPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    protected Photo createObject(ResultSet rset) throws SQLException {
        Preconditions.assertNotNullArgument(rset);

        return SerialKillerPhotoFactory.getInstance().createPhoto(rset);
    }
}
