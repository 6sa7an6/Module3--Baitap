package MiniProject.bussiness.daoimpl;

import MiniProject.bussiness.dao.IBookDao;
import MiniProject.bussiness.dao.IGenericDao;
import MiniProject.bussiness.model.Book;
import MiniProject.bussiness.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {

    @Override
    public List<Book> findByWord(String s) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement call;
        String sql;
        List<Book> list = new ArrayList<>();
        try {
            if (s == null) {
                sql = "select * FROM book where book_status = 1";
            } else {
                sql = "select * from book where (book_name like ? or book_title like ? or author_name like ?) and book_status = 1";
            }
            call = conn.prepareCall(sql);
            if (s != null) {
                call.setString(1, "%" + s + "%");
                call.setString(2, "%" + s + "%");
                call.setString(3, "%" + s + "%");
            }
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setBookId(rs.getString("book_id"));
                b.setBookName(rs.getString("book_name"));
                b.setBookTitle(rs.getString("book_title"));
                b.setDescription(rs.getString("description"));
                b.setAuthorName(rs.getString("author_name"));
                b.setPublishersName(rs.getString("publishers_name"));
                b.setPrice(rs.getBigDecimal("price"));
                b.setTotalPage(rs.getInt("total_pages"));
                b.setBookStatus(rs.getBoolean("book_status"));
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Book b) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement call;
        try {
            if (b.getBookId() == null) {
                // them moi
                call = conn.prepareCall("insert into book(book_name, book_title, description, author_name, publishers_name, price, total_pages, book_status) values (?,?,?,?,?,?,?,?)");
                call.setString(1, b.getBookName());
                call.setString(2, b.getBookTitle());
                call.setString(3, b.getDescription());
                call.setString(4, b.getAuthorName());
                call.setString(5, b.getPublishersName());
                call.setBigDecimal(6, b.getPrice());
                call.setInt(7, b.getTotalPage());
                call.setBoolean(8, b.isBookStatus());
                int count = call.executeUpdate();
                if (count > 0) {
                    System.out.println("Them moi thanh cong");
                } else {
                    System.err.println("Thong tin nhap vao khong dung !");
                }
            } else {
                // update
                call = conn.prepareCall("update book set book_name = ? , book_title = ?, description =?, author_name =?, publishers_name =?, price =?, total_pages =? where book_id = ? and book_status = 1");
                call.setString(1, b.getBookName());
                call.setString(2, b.getBookTitle());
                call.setString(3, b.getDescription());
                call.setString(4, b.getAuthorName());
                call.setString(5, b.getPublishersName());
                call.setBigDecimal(6, b.getPrice());
                call.setInt(7, b.getTotalPage());
                call.setString(8,b.getBookId());
                int count = call.executeUpdate();
                if (count > 0) {
                    System.out.println("Update thanh cong");
                } else {
                    System.err.println("Sach dang duoc muon khong the update !");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Book findById(String id) {
        Connection conn = ConnectDB.getConnection();
        Book b = new Book();
        try {
            CallableStatement call = conn.prepareCall("select * from book  where book_id = ? and book_status = 1");
            call.setString(1, id);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                b.setBookId(rs.getString("book_id"));
                b.setBookName(rs.getString("book_name"));
                b.setBookTitle(rs.getString("book_title"));
                b.setDescription(rs.getString("description"));
                b.setAuthorName(rs.getString("author_name"));
                b.setPublishersName(rs.getString("publishers_name"));
                b.setPrice(rs.getBigDecimal("price"));
                b.setTotalPage(rs.getInt("total_pages"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return b;
    }

    @Override
    public void delete(String id) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("DELETE from book where book_id = ? and book_status = 1");
            call.setString(1,id);
            int count = call.executeUpdate();
            if(count > 0 ){
                System.out.println("Xoa thanh cong !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
