package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {


    @Query("SELECT so FROM StockOrder so JOIN so.product p WHERE so.status = :status AND p.brand = :brand AND so.targetPrice <= :targetPrice")
    List<StockOrder> findStockOrdersByStatusAndBrandAndLessThanOrEqualTargetPrice(@Param("status") boolean status, @Param("brand") String brand, @Param("targetPrice") BigDecimal targetPrice);


    @Query("SELECT AVG(so.targetPrice) FROM StockOrder so")
    BigDecimal getTargetAvgBalance();


}