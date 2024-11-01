package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;


@Embeddable
@NoArgsConstructor
@PropertySource({"classpath:config/library.properties"})
public class BirthDateEntity {
    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    LocalDate birthDate;

}
