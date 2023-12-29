package MiniProject.bussiness.model;

import MiniProject.bussiness.util.Format;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import static MiniProject.bussiness.util.Format.spf;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    String orderId;
    String borrowerName;
    String BookId;
    Date orderAt;
    BigDecimal totalPrice;
    int borrowTime;
    boolean orderStatus;

    @Override
    public String toString() {
        return "[" +
                "orderId='" + orderId + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", BookId='" + BookId + '\'' +
                ", orderAt=" + spf.format(orderAt) +
                ", totalPrice=" + Format.formatCurrency(totalPrice) +
                ", borrowTime=" + borrowTime +
                ", orderStatus=" + (orderStatus? "Da Thanh Toan" : "Dang muon") +
                ']';
    }
    public String toStringBorrow() {
        return "[" +
                "orderId='" + orderId + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", BookId='" + BookId + '\'' +
                ", orderAt=" + spf.format(orderAt) +
                ']';
    }
}
