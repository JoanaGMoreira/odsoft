package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql;

import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.readermanagement.services.SearchReadersQuery;

import java.util.List;

interface ReaderDetailsRepoCustom {

    List<ReaderDetails> searchReaderDetails(pt.psoft.g1.psoftg1.shared.services.Page page, SearchReadersQuery query);
}
