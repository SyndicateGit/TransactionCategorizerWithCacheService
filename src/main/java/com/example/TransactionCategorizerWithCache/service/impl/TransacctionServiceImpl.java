package com.example.TransactionCategorizerWithCache.service.impl;

import com.example.TransactionCategorizerWithCache.entity.Transaction;
import com.example.TransactionCategorizerWithCache.service.TransactionServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransacctionServiceImpl implements TransactionServices {

    @Override
    public List<Transaction> categorizeTransactions(List<Transaction> transactions) {
        // TODO: Implement categorization logic
        return transactions;
    }
}
