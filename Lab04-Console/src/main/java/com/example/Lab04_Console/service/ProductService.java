package com.example.Lab04_Console.service;

import com.example.Lab04_Console.model.Category;
import com.example.Lab04_Console.model.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();
    private List<Category> listCategory = new ArrayList<>();

    public ProductService() {
        // Mock data
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Laptop"));

        listProduct.add(new Product(1, "iPhone 15", "demo.jpg", 20000L, listCategory.get(0)));
    }

    // Các hàm cho Category
    public List<Category> getAllCategories() {
        return listCategory;
    }

    public Category getCategory(int id) {
        return listCategory.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    // Các hàm cho Product
    public List<Product> getAll() {
        return listProduct;
    }

    public void add(Product newProduct) {
        int maxId = listProduct.stream().mapToInt(Product::getId).max().orElse(0);
        newProduct.setId(maxId + 1);
        listProduct.add(newProduct);
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (!imageProduct.isEmpty()) {
            try {
                // 1. Tạo thư mục uploads ở gốc dự án nếu chưa có
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 2. Sinh tên file mới
                String fileExtension = "";
                String originalName = imageProduct.getOriginalFilename();
                if (originalName != null && originalName.contains(".")) {
                    fileExtension = originalName.substring(originalName.lastIndexOf("."));
                }
                String newFileName = UUID.randomUUID().toString() + fileExtension;

                // 3. Lưu file vào thư mục uploads
                try (InputStream inputStream = imageProduct.getInputStream()) {
                    Path filePath = uploadPath.resolve(newFileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                // 4. Gán tên file cho object Product
                newProduct.setImage(newFileName);

            } catch (IOException e) {
                e.printStackTrace(); // Xử lý lỗi
            }
        }
    }
}