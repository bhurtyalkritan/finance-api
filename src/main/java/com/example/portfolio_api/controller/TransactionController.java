package com.example.portfolio_api.controller;

import com.example.portfolio_api.model.Transaction;
import com.example.portfolio_api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAssetId(@PathVariable Long assetId) {
        List<Transaction> transactions = transactionService.getTransactionsByAssetId(assetId);
        return ResponseEntity.ok(transactions);
    }
}
