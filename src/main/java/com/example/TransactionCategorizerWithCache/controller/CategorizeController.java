package com.example.TransactionCategorizerWithCache.controller;

import com.example.TransactionCategorizerWithCache.model.Transaction;
import com.example.TransactionCategorizerWithCache.service.TransactionServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Categorizes transactions based on transaction description and amount
@AllArgsConstructor
@RestController
@RequestMapping("/api/categorize")
public class CategorizeController {
    private TransactionServices transactionServices;
    @PostMapping
    public ResponseEntity<List<Transaction>> categorizeTransaction(@RequestBody List<Transaction> transactionDTO) {
        return new ResponseEntity<List<Transaction>>(transactionServices.categorizeTransactions(transactionDTO), HttpStatus.OK);
    }
}
