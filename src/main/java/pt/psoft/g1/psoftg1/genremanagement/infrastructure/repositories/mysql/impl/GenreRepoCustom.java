package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.impl;

import pt.psoft.g1.psoftg1.genremanagement.services.GenreLendingsDTO;
import pt.psoft.g1.psoftg1.genremanagement.services.GenreLendingsPerMonthDTO;

import java.time.LocalDate;
import java.util.List;

interface GenreRepoCustom {
    List<GenreLendingsPerMonthDTO> getLendingsPerMonthLastYearByGenre();

    List<GenreLendingsDTO> getAverageLendingsInMonth(LocalDate month, pt.psoft.g1.psoftg1.shared.services.Page page);

    List<GenreLendingsPerMonthDTO> getLendingsAverageDurationPerMonth(LocalDate startDate, LocalDate endDate);

}
