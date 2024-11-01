package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.NameEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    private static final long serialVersionUID = 1L;

    // database primary key
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name="USER_ID")
    private Long id;

    // optimistic lock concurrency control
    @Version
    @Getter
    @Setter
    private Long version;

    // auditing info
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    @Setter
    private LocalDateTime createdAt;

    // auditing info
    @LastModifiedDate
    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDateTime modifiedAt;

    // auditing info
    @CreatedBy
    @Column(nullable = false, updatable = false)
    @Getter
    @Setter
    private String createdBy;

    // auditing info
    @LastModifiedBy
    @Column(nullable = false)
    @Getter
    @Setter
    private String modifiedBy;

    @Setter
    @Getter
    private boolean enabled = true;

    @Setter
    @Column(unique = true, /*updatable = false,*/ nullable = false)
    @Email
    @Getter
    @NotNull
    @NotBlank
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    @NotNull
    @NotBlank
    private String password;

    @Getter
	@Setter
    @Embedded
    private NameEntity name;

    @ElementCollection
    @Getter
    private final Set<Role> authorities = new HashSet<>();



    public UserEntity() {
        // for ORM only
    }

}
