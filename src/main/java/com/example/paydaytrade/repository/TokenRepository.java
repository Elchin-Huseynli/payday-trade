package com.example.paydaytrade.repository;

import com.example.paydaytrade.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t inner join AppUser u on t.appUser.id = u.id " +
            "where u.id = :userId and (t.expired = false or t.revoked = false )")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
