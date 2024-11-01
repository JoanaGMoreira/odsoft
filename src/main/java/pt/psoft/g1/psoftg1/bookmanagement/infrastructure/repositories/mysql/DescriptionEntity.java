package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static pt.psoft.g1.psoftg1.bookmanagement.model.Description.DESC_MAX_LENGTH;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class DescriptionEntity {

    @Size(max = DESC_MAX_LENGTH)
    @Column(length = DESC_MAX_LENGTH)
    String description;

    protected DescriptionEntity() {}
}
