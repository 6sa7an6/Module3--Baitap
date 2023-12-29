package MiniProject.bussiness.model;

import MiniProject.bussiness.util.Format;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    String bookId;
    String bookName;
    String bookTitle;
    String description;
    String authorName;
    String publishersName;
    BigDecimal price;
    int totalPage;
    boolean bookStatus;

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", description='" + description + '\'' +
                ", authorName='" + authorName + '\'' +
                ", publishersName='" + publishersName + '\'' +
                ", price=" + Format.formatCurrency(price) +
                ", totalPage=" + totalPage +
                '}';
    }
    public String toStringIdAndName() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
