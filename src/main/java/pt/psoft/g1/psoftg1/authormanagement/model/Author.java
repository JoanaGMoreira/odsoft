package pt.psoft.g1.psoftg1.authormanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.StaleObjectStateException;
import pt.psoft.g1.psoftg1.authormanagement.services.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.exceptions.ConflictException;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.model.Name;

@Getter
@Setter
@NoArgsConstructor
public class Author extends EntityWithPhoto {

    private Long authorNumber;

    private Long version;

    private Name name;

    private Bio bio;


    public Author(String name, String bio, String photoURI) {
        setName(new Name(name));
        setBio( new Bio(bio));
        setPhotoInternal(photoURI);
    }

    public void applyPatch(final long desiredVersion, final UpdateAuthorRequest request) {
        if (this.version != desiredVersion)
            throw new StaleObjectStateException("Object was already modified by another user", this.authorNumber);
        if (request.getName() != null)
            setName(new Name(request.getName()));
        if (request.getBio() != null)
            setBio(new Bio(request.getBio()));
        if(request.getPhotoURI() != null)
            setPhotoInternal(request.getPhotoURI());
    }

    public void removePhoto(long desiredVersion) {
        if(desiredVersion != this.version) {
            throw new ConflictException("Provided version does not match latest version of this object");
        }

        setPhotoInternal(null);
    }
    public String getStringName() {
        return this.name.toString();
    }

    public String getStringBio() {
        return this.bio.toString();
    }
}

