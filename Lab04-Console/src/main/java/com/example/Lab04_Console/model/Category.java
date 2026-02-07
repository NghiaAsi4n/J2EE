package com.example.Lab04_Console.model; // Đổi theo package của bạn

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int id;
    private String name;
}