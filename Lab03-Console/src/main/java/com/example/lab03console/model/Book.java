package com.example.lab03console.model;

public class Book {
    private Long id;
    private String title;
    private String author;

    // Constructor không tham số (Bắt buộc để Thymeleaf bind dữ liệu từ form)
    public Book() {
    }

    // Constructor có tham số (Tiện cho việc khởi tạo dữ liệu mẫu)
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Các Getter và Setter (Bắt buộc để Spring và Thymeleaf truy cập thuộc tính)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
