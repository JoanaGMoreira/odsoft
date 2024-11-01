package pt.psoft.g1.psoftg1.readermanagement.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.readermanagement.model.PhoneNumber;

@Component
public class PhoneNumberMapper {
    public PhoneNumber map(String phoneNumber) {
        return new PhoneNumber(phoneNumber); // Adjust according to your PhoneNumber class
    }
}

