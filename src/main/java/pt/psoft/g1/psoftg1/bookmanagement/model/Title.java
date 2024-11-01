package pt.psoft.g1.psoftg1.bookmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Title {
    public static final int TITLE_MAX_LENGTH = 128;
    String title;

    public Title(String title) {
        setStringTitle(title);
    }

    public void setStringTitle(String title) {

        if(title == null)
            throw new IllegalArgumentException("Title cannot be null");
        if(title.isBlank())
            throw new IllegalArgumentException("Title cannot be blank");
        if(title.length() > TITLE_MAX_LENGTH)
            throw new IllegalArgumentException("Title has a maximum of " + TITLE_MAX_LENGTH + " characters");
        this.title = title.strip();
    }

    public String toString() {
        return this.title;
    }
}
