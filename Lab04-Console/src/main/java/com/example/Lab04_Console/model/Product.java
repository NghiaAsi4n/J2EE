package com.example.Lab04_Console.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống") // [cite: 275]
    private String name;

    @Length(min = 0, max = 200, message = "Tên hình ảnh không quá 200 kí tự") // [cite: 277]
    private String image;

    @NotNull(message = "Giá sản phẩm không được để trống") // [cite: 278]
    @Min(value = 1, message = "Giá sản phẩm không được nhỏ hơn 1") // [cite: 279]
    @Max(value = 9999999, message = "Giá sản phẩm không được lớn hơn 9999999") // [cite: 280]
    private long price;

    private Category category;
}