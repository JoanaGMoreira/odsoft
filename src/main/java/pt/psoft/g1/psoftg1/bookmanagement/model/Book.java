package pt.psoft.g1.psoftg1.bookmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.StaleObjectStateException;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.services.UpdateBookRequest;
import pt.psoft.g1.psoftg1.exceptions.ConflictException;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Book extends EntityWithPhoto {

    private Long version;
    private Isbn isbn;
    private Title title;
    private Genre genre;
    private List<Author> authors = new ArrayList<>();
    private Description description;

    private void setStringTitle(String title) {this.title = new Title(title);}

    private void setStringIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
    }

    private void setStringDescription(String description) {this.description = new Description(description); }

    private void setStringGenre(Genre genre) {this.genre = genre; }

    private void setStringAuthors(List<Author> authors) {this.authors = authors; }

    public String getStringDescription(){ return this.description.toString(); }

    public Book(String isbn, String title, String description, Genre genre, List<Author> authors, String photoURI) {
        setStringTitle(title);
        setStringIsbn(isbn);
        if(description != null)
            setStringDescription(description);
        if(genre==null)
            throw new IllegalArgumentException("Genre cannot be null");
        setStringGenre(genre);
        if(authors == null)
            throw new IllegalArgumentException("Author list is null");
        if(authors.isEmpty())
            throw new IllegalArgumentException("Author list is empty");

        setStringAuthors(authors);
        setPhotoInternal(photoURI);
    }

    public void removePhoto(long desiredVersion) {
        if(desiredVersion != this.version) {
            throw new ConflictException("Provided version does not match latest version of this object");
        }

        setPhotoInternal(null);
    }

    public void applyPatch(final Long desiredVersion, UpdateBookRequest request) {
        if (!Objects.equals(this.version, desiredVersion))
            throw new StaleObjectStateException("Object was already modified by another user ", getStringIsbn());

        String title = request.getTitle();
        String description = request.getDescription();
        Genre genre = request.getGenreObj();
        List<Author> authors = request.getAuthorObjList();
        String photoURI = request.getPhotoURI();
        if(title != null) {
            setStringTitle(title);
        }

        if(description != null) {
            setStringDescription(description);
        }

        if(genre != null) {
            setStringGenre(genre);
        }

        if(authors != null) {
            setStringAuthors(authors);
        }

        if(photoURI != null)
            setPhotoInternal(photoURI);

    }

    public String getStringIsbn(){
        return this.isbn.toString();
    }
}
