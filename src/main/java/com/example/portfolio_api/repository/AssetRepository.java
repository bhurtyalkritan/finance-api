// src/main/java/com/example/portfolio_api/repository/AssetRepository.java

package com.example.portfolio_api.repository;

import com.example.portfolio_api.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByPortfolioId(Long portfolioId);

    @Query("SELECT a FROM Asset a WHERE a.symbol = ?1")
    List<Asset> findBySymbol(String symbol);
}
