package com.example.paydaytrade.mapper;

import com.example.paydaytrade.model.dto.request.ProductRequestDto;
import com.example.paydaytrade.model.dto.response.ProductResponseDto;
import com.example.paydaytrade.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product productRequestDtoToProduct(ProductRequestDto productRequestDto);
    ProductResponseDto productToProductResponseDto(Product product);

}