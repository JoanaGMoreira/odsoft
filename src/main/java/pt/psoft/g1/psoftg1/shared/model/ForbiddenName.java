package pt.psoft.g1.psoftg1.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "forbidden_name")
@NoArgsConstructor
public class ForbiddenName{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Getter
    @Setter
    @Column(nullable = false)
    @Size(min = 1)
    private String forbiddenName;

    public ForbiddenName(String name) {
        this.forbiddenName = name;
    }
}
