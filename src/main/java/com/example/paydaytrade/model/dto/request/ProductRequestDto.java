package com.example.paydaytrade.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    Long id;

    String title;

    String description;

    Double price;

    Double discountPercentage;

    Double rating;

    Integer stock;

    String brand;

    String category;

    String thumbnail;

    List<String> images;

    Long thumbnailId;

    Long imageId;

    private static final AtomicLong thumbnailIdGenerator = new AtomicLong(1);
    private static final AtomicLong imageIdGenerator = new AtomicLong(1);

    ProductRequestDto() {
        thumbnailId = thumbnailIdGenerator.getAndIncrement();
        imageId = imageIdGenerator.getAndIncrement();
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    public Double getPrice() {
        return price;
    }


    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public Double getRating() {
        return rating;
    }


    public Integer getStock() {
        return stock;
    }


    public String getBrand() {
        return brand;
    }


    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public Long getThumbnailId() {
        return thumbnailId;
    }

    public Long getImageId() {
        return imageId;
    }
}