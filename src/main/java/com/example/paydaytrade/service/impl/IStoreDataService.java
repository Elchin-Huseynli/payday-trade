package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.mapper.ProductMapper;
import com.example.paydaytrade.model.dto.request.ProductRequestDto;
import com.example.paydaytrade.model.dto.request.StockDataRequestDTO;
import com.example.paydaytrade.model.dto.response.ProductResponseDto;
import com.example.paydaytrade.model.entity.Product;
import com.example.paydaytrade.model.feign.StockData;
import com.example.paydaytrade.repository.ProductRepository;
import com.example.paydaytrade.service.StoreDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import static com.example.paydaytrade.helper.StoreDataServiceHelper.*;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class IStoreDataService implements StoreDataService {

    private final StockData externalServiceClient;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Value("${custom.thumbnailUrl}")
    private String thumbnailUrl;

    @Value("${custom.imagesUrl}")
    private String imagesUrl;

    @Override
    public StockDataRequestDTO fetchAndStoreData() {
        return externalServiceClient.fetchData();
    }

    @Override
    public Page<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> responseDtos = products.stream()
                .map(product -> {
                    ProductResponseDto responseDto = productMapper.productToProductResponseDto(product);
                    responseDto.setThumbnail(thumbnailUrl + product.getThumbnailId() + ".jpg");
                    responseDto.setImages(List.of(imagesUrl + product.getImageId()));
                    return responseDto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(responseDtos);

    }

    @Override
    public List<ProductRequestDto> register() {
        StockDataRequestDTO data = fetchAndStoreData();
        List<ProductRequestDto> products = data.getProducts();

        photoFileWriter(products);

        products.stream()
                .map(productMapper::productRequestDtoToProduct)
                .forEach(productRepository::save);

        return products;
    }

}