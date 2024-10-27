package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.services.SearchBooksQuery;

import java.util.List;

interface BookRepoCustom {
    List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query);

}
