package com.example.paydaytrade.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockOrderRequest {

    @NotBlank(message = "Stock symbol cannot be blank")
    private String stockSymbol;

    @NotBlank(message = "Order type cannot be blank")
    private String order;

    @DecimalMin(value = "0.01", message = "Target price must be greater than or equal to 0.01")
    private BigDecimal targetPrice;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private boolean status;



    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

