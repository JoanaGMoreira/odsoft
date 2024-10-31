package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class MySQLEntityWithPhoto {
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="photo_id")
    protected PhotoEntity photo;
}
