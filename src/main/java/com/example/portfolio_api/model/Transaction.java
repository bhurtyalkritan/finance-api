// src/main/java/com/example/portfolio_api/model/Transaction.java

package com.example.portfolio_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    private int quantity;
    private double pricePerUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonBackReference
    private Asset asset;
}
