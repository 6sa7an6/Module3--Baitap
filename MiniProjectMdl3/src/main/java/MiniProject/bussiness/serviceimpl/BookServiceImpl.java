package MiniProject.bussiness.serviceimpl;

import MiniProject.bussiness.dao.IBookDao;
import MiniProject.bussiness.daoimpl.BookDaoImpl;
import MiniProject.bussiness.model.Book;
import MiniProject.bussiness.model.BookDTO;
import MiniProject.bussiness.service.IBookService;
import MiniProject.bussiness.util.ConnectDB;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements IBookService {
    public static IBookDao bookDao = new BookDaoImpl();

    @Override
    public void save(Book b) {
        bookDao.save(b);
    }

    @Override
    public List<Book> findByWord(String s) {
        return bookDao.findByWord(s);
    }

    @Override
    public Book findById(String id) {
        return bookDao.findById(id);
    }

    @Override
    public void delete(String id) {
        bookDao.delete(id);
    }

    @Override
    public Integer borrowBookCount() {
        Connection conn = ConnectDB.getConnection();
        Integer count = null;
        try {
            CallableStatement call = conn.prepareCall("select count(*) as count from `order` where MONTH(order_at) = MONTH(now()) and YEAR(order_at) = YEAR(now())");
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                return count;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return count;
    }

    @Override
    public BigDecimal revenueOfLibaryAtYear() {
        Connection conn = ConnectDB.getConnection();
        BigDecimal total = null;
        try {
            CallableStatement call = conn.prepareCall("select SUM(total_price) as total from `order` where YEAR(order_at) = YEAR(now()) and order_status = 1");
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal("total");
                return total;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return total;
    }

    @Override
    public List<BookDTO> borrowCountTop5() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Select b.book_id book_id , b.book_name book_name , count(*) as borrow_count from book b join `order` o on b.book_id = o.book_id group by b.book_id order by borrow_count desc limit 5;");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BookDTO b = new BookDTO();
                b.setBookId(rs.getString("book_id"));
                b.setBookName(rs.getString("book_name"));
                b.setCount(rs.getInt("borrow_count"));
                bookDTOList.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return bookDTOList;
    }
}
