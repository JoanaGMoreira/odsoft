package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.myslq.impl;

import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.shared.services.Page;

import java.time.LocalDate;
import java.util.List;

interface LendingRepoCustom {
    List<Lending> getOverdue(Page page);

    List<Lending> searchLendings(Page page, String readerNumber, String isbn, Boolean returned, LocalDate startDate, LocalDate endDate);
//    List<ReaderAverageDto> getAverageMonthlyPerReader(LocalDate startDate, LocalDate endDate);

}
