package MiniProject.present;

import MiniProject.bussiness.model.Book;
import MiniProject.bussiness.model.BookDTO;
import MiniProject.bussiness.model.Order;
import MiniProject.bussiness.service.IBookService;
import MiniProject.bussiness.service.IOrderService;
import MiniProject.bussiness.serviceimpl.BookServiceImpl;
import MiniProject.bussiness.serviceimpl.OrderServiceImpl;
import MiniProject.bussiness.util.Format;
import MiniProject.bussiness.util.InputMethods;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookManager {
    public static IBookService bookService = new BookServiceImpl();
    public static IOrderService orderService = new OrderServiceImpl();
    public static void main(String[] args) {
        while (true) {
            System.out.println("----------Menu---------");
            System.out.println("1.Them sach moi");
            System.out.println("2.Tim kiem sach");
            System.out.println("3.Muon sach");
            System.out.println("4.Tra sach");
            System.out.println("5.Xem thong tin chi tiet sach");
            System.out.println("6.Chinh sua thong tin sach");
            System.out.println("7.Xoa sach theo ma sach ");
            System.out.println("8.Thong ke so luong sach muon trong thang hien hanh");
            System.out.println("9.Tinh doanh thu cua thu vien tu dau nam den nay ( chi nhung hoa don da thanh toan )");
            System.out.println("10.Hien thi top 5 nhung cuon sach duoc muon nhieu nhat .");
            System.out.println("0.Exit");
            System.out.print("Hay nhap lua chon cua ban : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    findBookByWord();
                    break;
                case 3:
                    borrow();
                    break;
                case 4:
                    giveBack();
                    break;
                case 5:
                    showBookById();
                    break;
                case 6:
                    update();
                    break;
                case 7 :
                    delete();
                    break;
                case 8 :
                    borrowBookAtMonth();
                    break;
                case 9 :
                    totalBorrowBookAtYear();
                    break;
                case 10:
                    showTop5BorrowBook();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Hay nhap tu 0 -> 5");
            }
        }
    }
    public static void add(){
        System.out.println("------Them Sach-----");
        Book book = new Book();
        System.out.print("Hay nhap ten sach : ");
        book.setBookName(InputMethods.getString());
        System.out.print("Hay nhap title sach : ");
        book.setBookTitle(InputMethods.getString());
        System.out.print("Hay nhap mo ta sach : ");
        book.setDescription(InputMethods.getString());
        System.out.print("Hay nhap ten tac gia : ");
        book.setAuthorName(InputMethods.getString());
        System.out.print("Hay nhap ten nha xuat ban : ");
        book.setPublishersName(InputMethods.getString());
        System.out.print("Hay nhap gia thue : ");
        book.setPrice(InputMethods.getBigDecimal());
        System.out.print("Hay nhap so luong trang : ");
        book.setTotalPage(InputMethods.getInteger());
        book.setBookStatus(true);
        bookService.save(book);
    }
    public static void findBookByWord(){
        System.out.println("---------Tim Kiem--------");
        System.out.print("Hay nhap keyword muon tim kiem : ");
        String key = InputMethods.getString();
        List<Book> books = bookService.findByWord(key);
        books.forEach(System.out::println);
    }
    public static void borrow(){
        System.out.println("Thong tin sach hien co : ");
        List<Book> books = bookService.findByWord(null);
        books.forEach(book -> System.out.println(book.toStringIdAndName()));
        Order order = new Order();
        System.out.print("Hay nhap ID sach muon muon : ");
        String id = InputMethods.getString();
        if(bookService.findById(id) != null) {
            System.out.print("Hay nhap ten nguoi muon : ");
            String name = InputMethods.getString();
            order.setBookId(id);
            order.setBorrowerName(name);
            order.setOrderAt(new Date());
            orderService.save(order);
        }else{
            System.err.println("Khong co Book ID nay !");
        }
    }
    public static void giveBack(){
        System.out.print("Hay nhap orderID cua ban : ");
        String id = InputMethods.getString();
        Order o = orderService.findById(id);
        if(o != null){
            orderService.save(o);
        }else {
            System.err.println("OrderID khong ton tai ! .");
        }
    }
    public static void showBookById(){
        System.out.print("Hay nhap ID book ban can xem thong tin : ");
        String id = InputMethods.getString();
        if(bookService.findById(id) != null) {
            System.out.println(bookService.findById(id));
        }else {
            System.err.println("ID khong ton tai !");
        }
    }
    public static void update(){
        System.out.print("Hay nhap ID sach can update : ");
        String id = InputMethods.getString();
        Book b = bookService.findById(id);
        if(b != null){
            System.out.print("Hay nhap ten sach moi : ");
            b.setBookName(InputMethods.getString());
            System.out.print("Hay nhap title sach moi : ");
            b.setBookTitle(InputMethods.getString());
            System.out.println("Hay nhap mo ta sach : ");
            b.setDescription(InputMethods.getString());
            System.out.println("Hay nhap ten tac gia : ");
            b.setAuthorName(InputMethods.getString());
            System.out.println("Hay nhap ten nha san xuat : ");
            b.setPublishersName(InputMethods.getString());
            System.out.println("Hay nhap gia sach : ");
            b.setPrice(InputMethods.getBigDecimal());
            System.out.println("Hay nhap so luong trang : ");
            b.setTotalPage(InputMethods.getInteger());
            b.setBookStatus(true);
            bookService.save(b);;
        }else{
            System.err.println("ID khong ton tai hoac sach da duoc muon !");
        }
    }
    public static void delete(){
        System.out.println("Hay nhap ID sach can xoa : ");
        String id = InputMethods.getString();
        Book b = bookService.findById(id);
        if(b != null){
            bookService.delete(id);
        }else {
            System.err.println("ID khong ton tai hoac sach dang duoc muon ! ");
        }
    }
    public static void borrowBookAtMonth() {
        Integer count = bookService.borrowBookCount();
        if (count != null) {
            System.out.println("So Sach Muon trong thang nay la : " + count);
        }else {
            System.out.println("Thang nay chua co quyen sach nao duoc muon !");
        }
    }
    public static void totalBorrowBookAtYear(){
        BigDecimal total = bookService.revenueOfLibaryAtYear();
        if(total != null){
            System.out.println("Doanh thu cua thu vien tu dau nam den nay la : " + Format.formatCurrency(total));
        }else {
            System.out.println("Tu dau nam den nay thu vien chua cho thue quyen nao .");
        }
    }
    public static void showTop5BorrowBook(){
        List<BookDTO> bookDTOList = bookService.borrowCountTop5();
        bookDTOList.forEach(System.out::println);
    }
}
