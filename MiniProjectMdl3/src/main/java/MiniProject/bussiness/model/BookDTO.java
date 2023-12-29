package MiniProject.bussiness.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDTO {
    String bookId;
    String bookName;
    Integer count;

    @Override
    public String toString() {
        return "[" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", So lan muon=" + count +
                ']';
    }
}