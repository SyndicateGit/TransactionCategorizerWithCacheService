package com.example.TransactionCategorizerWithCache.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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
