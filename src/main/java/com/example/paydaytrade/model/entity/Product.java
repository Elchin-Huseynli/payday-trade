package com.example.paydaytrade.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    Double price;

    Double discountPercentage;

    Double rating;

    Integer stock;

    String brand;

    String category;

    Long thumbnailId;

    Long imageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setPrice(Double price) {
        this.price = price;
    }



    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }



    public void setRating(Double rating) {
        this.rating = rating;
    }


    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public Long getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(Long thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
