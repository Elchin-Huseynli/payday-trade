package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByEmailAndIsEnable(String email, boolean isEnable);
    Boolean existsByEmail(String email);
    Optional<AppUser> findByEmailIgnoreCase(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser u SET u.depositBalance = u.depositBalance + :payment WHERE u.email = :email AND :payment >= 1")
    void updateDepositBalance(@Param("payment") BigDecimal payment, @Param("email") String email);

}