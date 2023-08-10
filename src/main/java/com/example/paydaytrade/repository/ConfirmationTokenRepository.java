package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String confirmationToken);

}
