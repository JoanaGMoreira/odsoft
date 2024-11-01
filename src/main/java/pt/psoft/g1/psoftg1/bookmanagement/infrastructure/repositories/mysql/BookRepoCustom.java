package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.services.SearchBooksQuery;
import pt.psoft.g1.psoftg1.shared.services.Page;

import java.util.List;

interface BookRepoCustom {
    List<BookEntity> searchBooks(Page page, SearchBooksQuery query);

}
