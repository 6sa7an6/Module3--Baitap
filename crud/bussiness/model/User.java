package crud.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    Long id;
    String name;
    String phone;
    String address;
    boolean status;
    int roleId;
}
