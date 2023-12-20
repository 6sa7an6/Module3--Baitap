package crud.present;

import crud.bussiness.model.User;
import crud.bussiness.service.IUserService;
import crud.bussiness.serviceimpl.UserServiceImpl;
import crud.bussiness.util.InputMethods;

import java.util.List;
import java.util.Scanner;

public class UserManager {
    private static IUserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        while (true){
            System.out.println("-------Menu------");
            System.out.println("1.Create");
            System.out.println("2.Read");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.FindByName");
            System.out.println("0.Exit");
            System.out.print("Hay nhap lua chon cua ban : ");;
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    createUser();
                    break;
                case 2:
                    displayAllUser();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    findByName();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Hay nhap tu 0->5");
            }
        }
    }
    public static void createUser(){
        User user = new User();
        System.out.println("Hay nhap role : ");
        String role = InputMethods.getString();
        System.out.println("Hay nhap ten : ");
        user.setName(InputMethods.getString());
        System.out.println("Hay nhap so dien thoai : ");
        user.setPhone(InputMethods.getString());
        System.out.println("Hay nhap dia chi : ");
        user.setAddress(InputMethods.getString());
        System.out.println("Hay nhap trang thai : ");
        user.setStatus(InputMethods.getBoolean());
        userService.add(user,role);
    }
    public static void update(){
        System.out.println("Hay nhap id user can sua doi : ");
        Long id = InputMethods.getLong();
        User user = userService.findById(id);
        if(user != null){
            System.out.println("Thong tin sinh vien : ");
            System.out.println(user);
            System.out.println("Hay nhap ten moi : ");
            user.setName(InputMethods.getString());
            System.out.println("Hay nhap so dien thoai moi : ");
            user.setPhone(InputMethods.getString());
            System.out.println("Hay nhap dia chi moi : ");
            user.setAddress(InputMethods.getString());
            System.out.println("Hay nhap trang thai moi : ");
            user.setStatus(InputMethods.getBoolean());
            System.out.println("Hay nhap role moi : ");
            user.setRoleId(InputMethods.getInteger());
            userService.update(user);
        }else {
            System.err.println("Id khong ton tai !");
        }
    }
    public static void delete(){
        System.out.println("Hay nhap id user can xoa : ");
        Long id = InputMethods.getLong();
        userService.delete(id);
    }
    public static void findByName(){
        System.out.println("Hay nhap ten can tim kiem : ");
        String name = InputMethods.getString();
        List<User> list = userService.findByName(name);
        if(list.isEmpty()){
            System.err.println("Khong co user nao hop le !");
        }else {
            list.forEach(System.out::println);
        }
    }
    public static void displayAllUser(){
        List<User> list = userService.findAll();
        if(list.isEmpty()){
            System.err.println("Danh sach trong !");
        }else{
            list.forEach(System.out::println);
        }
    }
}
