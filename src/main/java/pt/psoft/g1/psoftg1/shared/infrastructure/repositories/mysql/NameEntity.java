package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Embeddable
@PropertySource({"classpath:config/library.properties"})
@AllArgsConstructor
public class NameEntity {
    @NotNull
    @NotBlank
    @Column(name="NAME", length = 150)
    private String name;
    protected NameEntity() {
        // for ORM only
    }
}
