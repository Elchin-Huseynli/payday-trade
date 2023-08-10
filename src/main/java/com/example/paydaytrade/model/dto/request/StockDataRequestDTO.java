package com.example.paydaytrade.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockDataRequestDTO {
    List<ProductRequestDto> products;

    Integer total;

    Integer skip;

    Integer limit;
}