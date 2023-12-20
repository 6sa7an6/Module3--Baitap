package crud.bussiness.daoimpl;

import crud.bussiness.dao.IUserDao;
import crud.bussiness.model.User;
import crud.bussiness.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM user");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getLong("user_id"));
                u.setName(rs.getString("full_name"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setStatus(rs.getBoolean("status"));
                u.setRoleId(rs.getInt("role_id"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void add(User user , String roleName) {
        Connection conn = ConnectDB.getConnection();
        try {
                CallableStatement call = conn.prepareCall("SELECT id_role FROM role where role_name = ?");
                call.setString(1,roleName);
                ResultSet rs = call.executeQuery();
                if(rs.next()){
                    CallableStatement call1 = conn.prepareCall("insert into user (full_name, phone, address, status,role_id) values (?,?,?,?,?)");
                    call1.setString(1,user.getName());
                    call1.setString(2,user.getPhone());
                    call1.setString(3,user.getAddress());
                    call1.setBoolean(4,user.isStatus());
                    call1.setInt(5,rs.getInt("id_role"));
                    int count = call1.executeUpdate();
                    if(count >0 ){
                        System.out.println("Them moi thanh cong !");
                    }

                }else {
                    System.err.println("Role khong ton tai !");
                }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public User findById(Long id) {
        User u = new User();
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM User where user_id = ?");
            call.setLong(1,id);
            ResultSet rs = call.executeQuery();
            if (rs.next()){
                u.setId(id);
                u.setName(rs.getString("full_name"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setStatus(rs.getBoolean("status"));
                u.setRoleId(rs.getInt("role_id"));
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return u;
    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("DELETE FROM User where user_id = ?");
            call.setLong(1,id);
            int count = call.executeUpdate();
            if(count>0){
                System.out.println("Xoa Thanh cong");
            }else {
                System.err.println("ID khong ton tai !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void update(User user) {
        Connection conn = ConnectDB.getConnection();
        try {
                CallableStatement call = conn.prepareCall("update user set full_name = ?, phone = ?, address = ?, status = ?,role_id = ? where user_id = ?");
                call.setString(1,user.getName());
                call.setString(2,user.getPhone());
                call.setString(3,user.getAddress());
                call.setBoolean(4,user.isStatus());
                call.setInt(5,user.getRoleId());
                call.setLong(6,user.getId());
                int count = call.executeUpdate();
                if(count >0 ){
                    System.out.println("Update thanh cong !");
                }else{
                    System.err.println("Thong tin khong hop le !");
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public List<User> findByName(String name) {
        List<User> list = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM user where full_name like ?");
            call.setString(1,"%"+name+"%");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getLong("user_id"));
                u.setName(rs.getString("full_name"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setStatus(rs.getBoolean("status"));
                u.setRoleId(rs.getInt("role_id"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
}
