// src/main/java/com/example/portfolio_api/repository/PortfolioRepository.java

package com.example.portfolio_api.repository;

import com.example.portfolio_api.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByUserId(Long userId);

    @Query("SELECT p FROM Portfolio p WHERE p.portfolioType = ?1")
    List<Portfolio> findByPortfolioType(String portfolioType);
}
