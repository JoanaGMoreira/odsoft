package pt.psoft.g1.psoftg1.readermanagement.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.readermanagement.model.BirthDate;

@Component
public class BirthDateMapper {
    public BirthDate map(String birthDate) {
        return new BirthDate(birthDate);
    }
}
