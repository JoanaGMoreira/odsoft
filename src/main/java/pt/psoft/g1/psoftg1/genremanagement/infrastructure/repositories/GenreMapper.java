package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.GenreEntity;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;

public class GenreMapper {
    public static GenreEntity toEntity(Genre genre) {
        GenreEntity entity = new GenreEntity();
        entity.setPk(genre.getPk());
        entity.setGenre(genre.getGenre());
        return entity;
    }

    // Convert MySQL Entity to Service Model
    public static Genre toModel(GenreEntity entity) {
        Genre genre = new Genre(entity.getGenre());
        genre.setPk(entity.getPk());
        return genre;
    }
}
