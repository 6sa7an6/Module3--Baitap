package bank.daoimpl;

import bank.dao.IManagerDao;
import bank.model.Account;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static bank.Main.connection;

public class ManagerDaoImpl implements IManagerDao {
    @Override
    public void create() {
        try {
            CallableStatement callableStatement = connection.prepareCall("insert into accounts (id,account_holder_id,balance) values (?,?,?)");
            System.out.println("Nhap id");
            callableStatement.setInt(1,new Scanner(System.in).nextInt());
            System.out.println("Nhap holder_id");
            callableStatement.setInt(2,new Scanner(System.in).nextInt());
            System.out.println("Nhap balance");
            callableStatement.setDouble(3,new Scanner(System.in).nextDouble());
            int count = callableStatement.executeUpdate();
            if(count>0){
                System.out.println("Create thanh cong");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void read() {
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * from accounts");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setAccount_holder_id(rs.getInt("account_holder_id"));
                account.setBalance(rs.getDouble("balance"));
                System.out.println(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            CallableStatement call = connection.prepareCall("update accounts set account_holder_id = ?,balance = ? where id = ?");
            System.out.println("Nhập id cần update : ");
            int id = new Scanner(System.in).nextInt();
            if(checkId(id)){
                call.setInt(3,id);
                System.out.println("Nhập Holder ID mới : ");
                call.setInt(1,new Scanner(System.in).nextInt());
                System.out.println("Nhập balance mới : ");
                call.setDouble(2,new Scanner(System.in).nextDouble());
                int count = call.executeUpdate();
                if (count>0) {
                    System.out.println("Update thanh cong");
                }
            }else {
                System.out.println("Khong tim thay id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            CallableStatement call = connection.prepareCall("Delete from accounts where id = ?");
            System.out.println("Nhập ID cần xóa : ");
            int id = new Scanner(System.in).nextInt();
            if(checkId(id)){
                call.setInt(1,id);
                int count = call.executeUpdate();
                if(count>0){
                    System.out.println("Xóa thành công");
                }
            }else {
                System.err.println("Không có ID này!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkId(int idCheck) {
        try {
            CallableStatement call = connection.prepareCall("Select * from accounts where id = ?");
            call.setInt(1, idCheck);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
