package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialKillerPhotoFactory extends PhotoFactory {
    @Override
    public Photo createPhoto() {
        return new SerialKillerPhoto();
    }

    @Override
    public Photo createPhoto(PhotoId id) {
        return new SerialKillerPhoto(id);
    }

    @Override
    public Photo createPhoto(ResultSet rs) throws SQLException {
        return new SerialKillerPhoto(rs);
    }
}
