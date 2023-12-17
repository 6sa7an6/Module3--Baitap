package bank;

import bank.model.Account;
import bank.service.IService;
import bank.serviceimpl.ServiceImpl;

import java.sql.*;
import java.util.Scanner;

public class Main {
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/bank";
    final static String USERNAME = "root";
    final static String PASSWORD = "Vjtc0nd3pz4j";
    public static Connection connection = getConnection();

    public static void main(String[] args) {
        IService service = new ServiceImpl();
        while (true) {
            System.out.println("--Menu--");
            System.out.println("1.Create");
            System.out.println("2.Read");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("0.Exit");
            System.out.print("Nhap Lua Chon : ");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    service.creat();
                    break;
                case 2:
                    service.read();
                    break;
                case 3:
                    service.update();
                    break;
                case 4:
                    service.delete();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Nhap sai hay nhap lai !");
            }
        }
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
