package com.example.portfolio_api.controller;

import com.example.portfolio_api.model.Asset;
import com.example.portfolio_api.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<Asset>> getAssetsByPortfolioId(@PathVariable Long portfolioId) {
        List<Asset> assets = assetService.getAssetsByPortfolioId(portfolioId);
        return ResponseEntity.ok(assets);
    }
}
