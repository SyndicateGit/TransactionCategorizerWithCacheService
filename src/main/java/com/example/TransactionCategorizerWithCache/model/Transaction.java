package com.example.TransactionCategorizerWithCache.model;

import lombok.*;

import java.math.BigDecimal;

// @Entity At the moment not an entity. Not recorded in the database
// Only categories are.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private String id;

    private String description;

    private BigDecimal amount;

    private String date;

    private String category;
}
