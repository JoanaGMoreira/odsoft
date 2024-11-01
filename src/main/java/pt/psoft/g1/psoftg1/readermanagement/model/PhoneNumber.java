package pt.psoft.g1.psoftg1.readermanagement.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneNumber(String number) {
        if(!(number.startsWith("9") || number.startsWith("2")) || number.length() != 9) {
            throw new IllegalArgumentException("Phone number is not valid: " + number);
        }

        this.phoneNumber = number;
    }

    public String toString() {
        return this.phoneNumber;
    }
}
