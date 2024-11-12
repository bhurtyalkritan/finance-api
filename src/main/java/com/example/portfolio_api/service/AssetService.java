package com.example.portfolio_api.service;

import com.example.portfolio_api.model.Asset;
import com.example.portfolio_api.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAssetsByPortfolioId(Long portfolioId) {
        return assetRepository.findByPortfolioId(portfolioId);
    }
}
