package MiniProject.bussiness.daoimpl;

import MiniProject.bussiness.dao.IOrderDao;
import MiniProject.bussiness.model.Order;
import MiniProject.bussiness.util.ConnectDB;
import MiniProject.bussiness.util.InputMethods;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class OderDaoImpl implements IOrderDao {
    @Override
    public void save(Order order) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement call;
        try {
            if (order.getOrderId() == null) {
                // Muon sach
                String updateSql = "UPDATE book b join `order` o on b.book_id = o.book_id set b.book_status = false where b.book_id = ?";
                CallableStatement callId = conn.prepareCall(updateSql);
                callId.setString(1, order.getBookId());
                callId.executeUpdate();
                String insertSql = "insert into `order` (borrower_name, book_id, order_at, order_status) values (?,?,?,?)";
                call = conn.prepareCall(insertSql);
                call.setString(1, order.getBorrowerName());
                call.setString(2, order.getBookId());
                call.setDate(3, new Date(order.getOrderAt().getTime()));
                call.setBoolean(4, false);
                int count = call.executeUpdate();
                if (count > 0) {
                    System.out.println("Thue sach thanh cong");
                }else{
                    System.err.println("Thong tin loi");
                }
                String findId = "SELECT order_id from `order` where book_id = ?";
                CallableStatement callFindId = conn.prepareCall(findId);
                callFindId.setString(1,order.getBookId());
                ResultSet rs = callFindId.executeQuery();
                if(rs.next()){
                    order.setOrderId(rs.getString("order_id"));
                }
                System.out.println("Hoa don cua ban la : " + order.toStringBorrow());
            }else {
                // Tra sach
                String getPriceSql = "SELECT b.price  FROM book b join `order` o on b.book_id = o.book_id where o.order_id = ?";
                CallableStatement callGetPriceSql = conn.prepareCall(getPriceSql);
                callGetPriceSql.setString(1,order.getOrderId());
                ResultSet rs = callGetPriceSql.executeQuery();
                while (rs.next()) {
                    String updateSql = "UPDATE book b join `order` o on b.book_id = o.book_id set b.book_status = true , o.order_status = true , o.borrow_time = ? , o.total_price = ? where o.order_id = ?";
                    call = conn.prepareCall(updateSql);
                    long curr = new java.util.Date().getTime();
                    long borrowTimeMili = curr - order.getOrderAt().getTime();
                    int borrowTime = (int) (borrowTimeMili/(24*60*60*1000));
                    call.setInt(1,borrowTime);
                    BigDecimal totalPrice = rs.getBigDecimal("price").multiply(new BigDecimal(borrowTime));
                    call.setBigDecimal(2, totalPrice);
                    call.setString(3,order.getOrderId());
                    order.setBorrowTime(borrowTime);
                    order.setTotalPrice(totalPrice);
                    order.setOrderStatus(true);
                    int count = call.executeUpdate();
                    if (count > 0) {
                        System.out.println("Tra sach thanh cong");
                    }
                    System.out.println("Hoa don cua ban la : " + order);
                }
            }
            } catch (SQLException e) {
            System.err.println(e.getStackTrace());
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnection(conn);
            }
        }

    @Override
    public Order findById(String id) {
        Connection conn = ConnectDB.getConnection();
        Order o = new Order();
        try {
            CallableStatement call = conn.prepareCall("select * from `order` where order_id = ? and order_status = 0");
            call.setString(1,id);
            ResultSet rs = call.executeQuery();
            if (rs.next()){
                o.setOrderId(id);
                o.setBorrowerName(rs.getString("borrower_name"));
                o.setBookId(rs.getString("book_id"));
                o.setOrderAt(rs.getDate("order_at"));
                o.setTotalPrice(rs.getBigDecimal("total_price"));
                o.setBorrowTime(rs.getInt("borrow_time"));
                o.setOrderStatus(rs.getBoolean("order_status"));
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return o;
    }
}