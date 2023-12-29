package MiniProject.bussiness.service;

import MiniProject.bussiness.model.Book;
import MiniProject.bussiness.model.BookDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IBookService {
    void save(Book b);
    List<Book> findByWord(String s);
    Book findById(String id);
    void delete (String id);
    Integer borrowBookCount();
    BigDecimal revenueOfLibaryAtYear();
    List<BookDTO> borrowCountTop5();
}
