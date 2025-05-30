package com.desafiobackendpicpay.controllers;

import com.desafiobackendpicpay.dtos.TransactionDTO;
import com.desafiobackendpicpay.services.TransactionService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class Transactioncontroller {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = (Transaction) this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
