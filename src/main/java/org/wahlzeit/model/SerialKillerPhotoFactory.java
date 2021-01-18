package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.PatternInstance;
import org.wahlzeit.utils.conditions.Preconditions;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteFactory"
	}
)
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
        Preconditions.assertNotNullArgument(rs);

        return new SerialKillerPhoto(rs);
    }
}
