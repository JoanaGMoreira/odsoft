package pt.psoft.g1.psoftg1.readermanagement.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

@Component
public class ReaderNumberMapper {
    public ReaderNumber map(int readerNumber) {
        return new ReaderNumber(readerNumber);
    }
}
