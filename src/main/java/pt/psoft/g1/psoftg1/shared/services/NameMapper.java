package pt.psoft.g1.psoftg1.shared.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.shared.model.Name;

@Component
public class NameMapper {
    public Name map(String name) {
        return new Name(name);
    }
}
