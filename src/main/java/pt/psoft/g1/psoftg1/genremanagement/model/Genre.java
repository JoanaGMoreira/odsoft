package pt.psoft.g1.psoftg1.genremanagement.model;

import lombok.Getter;
import lombok.Setter;


public class Genre {
    public static final int GENRE_MAX_LENGTH = 100;

    @Getter
    @Setter
    long pk;
    @Getter
    @Setter
    String genre;

    public Genre(){}

    public Genre(String genre) {
        setStringGenre(genre);
    }

    private void setStringGenre(String genre) {
        if(genre == null)
            throw new IllegalArgumentException("Genre cannot be null");
        if(genre.isBlank())
            throw new IllegalArgumentException("Genre cannot be blank");
        if(genre.length() > GENRE_MAX_LENGTH)
            throw new IllegalArgumentException("Genre has a maximum of 4096 characters");
        this.genre = genre;
    }

    public String toString() {
        return genre;
    }
}
