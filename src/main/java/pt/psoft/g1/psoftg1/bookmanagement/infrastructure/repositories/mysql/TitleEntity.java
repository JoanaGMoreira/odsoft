package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static pt.psoft.g1.psoftg1.bookmanagement.model.Title.TITLE_MAX_LENGTH;

@Getter
@Setter
@Embeddable
public class TitleEntity {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = TITLE_MAX_LENGTH)
    @Column(name="TITLE", length = TITLE_MAX_LENGTH)
    String title;

    public TitleEntity() {}
}
