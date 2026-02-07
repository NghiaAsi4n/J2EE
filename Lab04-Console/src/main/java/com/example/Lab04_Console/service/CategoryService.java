package com.example.Lab04_Console.service;

import com.example.Lab04_Console.model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> listCategory = new ArrayList<>();

    public CategoryService() {
        // Mock data cho Category
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Laptop"));
    }

    public List<Category> getAll() {
        return listCategory;
    }

    public Category get(int id) {
        return listCategory.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}