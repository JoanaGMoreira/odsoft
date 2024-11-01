package pt.psoft.g1.psoftg1.authormanagement.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.model.Bio;

@Component
public class BioMapper {
    public Bio map(String bio) {
        return new Bio(bio);
    }
}
