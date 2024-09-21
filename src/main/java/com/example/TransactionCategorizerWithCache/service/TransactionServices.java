package com.example.TransactionCategorizerWithCache.service;

import com.example.TransactionCategorizerWithCache.model.Transaction;

import java.util.List;

public interface TransactionServices {
    List<Transaction> categorizeTransactions (List<Transaction> transactions);
}
