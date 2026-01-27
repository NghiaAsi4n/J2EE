import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int chon;
        listBook.add(new Book(101, "Lập trình Java căn bản", "Nguyễn Văn A", 150000));
        listBook.add(new Book(102, "Cấu trúc dữ liệu và giải thuật", "Nguyễn Văn B", 200000));
        listBook.add(new Book(103, "Nhập môn J2EE và Spring Boot", "Nguyễn Văn C", 250000));
        listBook.add(new Book(104, "Thiết kế Web với ReactJS", "Nguyễn Văn D", 180000));
        listBook.add(new Book(105, "Cơ sở dữ liệu SQL Server", "Nguyễn Văn E", 220000));
        listBook.add(new Book(106, "Tư duy lập trình hiện đại", "Nguyễn Văn F", 120000));
        listBook.add(new Book(107, "Lập trình hướng đối tượng (OOP)", "Nguyễn Văn G", 190000));

        String menu = """
                --- CHƯƠNG TRÌNH QUẢN LÝ SÁCH ---
                1. Thêm 1 cuốn sách
                2. Xóa 1 cuốn sách
                3. Thay đổi sách
                4. Xuất thông tin
                5. Tìm sách Lập trình
                6. Lấy sách tối đa theo giá
                7. Tìm kiếm theo tác giả
                0. Thoát
                Chọn chức năng: """;

        do {
            System.out.print(menu);
            chon = sc.nextInt();
            switch (chon) {
                case 1 -> {
                    Book b = new Book();
                    b.input();
                    listBook.add(b);
                }
                case 2 -> {
                    System.out.print("Nhập vào mã sách cần xóa: ");
                    int id = sc.nextInt();
                    listBook.removeIf(b -> b.getId() == id);
                    System.out.println("Đã xóa sách thành công");
                }
                case 3 -> {
                    System.out.print("Nhập vào mã sách cần điều chỉnh: ");
                    int bookid = sc.nextInt();
                    listBook.stream()
                            .filter(b -> b.getId() == bookid)
                            .findFirst()
                            .ifPresentOrElse(
                                    book -> {
                                        System.out.println("Đã tìm thấy sách: " + book.getTitle());
                                        System.out.println("Nhập thông tin mới: ");
                                        book.input();
                                        System.out.println("Cập nhật thành công!");
                                    },
                                    () -> System.out.println("Không tìm thấy sách có mã: " + bookid)
                            );
                }
                case 4 -> {
                    System.out.print("\nXuất thông tin danh sách ");
                    listBook.forEach(Book::output); // Method Reference
                }
                case 5 -> {
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhập số lượng sách K: "); int k = sc.nextInt();
                    System.out.print("Nhập giá tối đa P: "); long p = sc.nextLong();
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    sc.nextLine();
                    System.out.print("Nhập danh sách tác giả: ");
                    String input = sc.nextLine();
                    Set<String> authSet = Set.of(input.split(",\\s*"));
                    listBook.stream()
                            .filter(b -> authSet.contains(b.getAuthor()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Chọn sai, vui lòng chọn lại!");
            }
        } while (chon != 0);
    }
}