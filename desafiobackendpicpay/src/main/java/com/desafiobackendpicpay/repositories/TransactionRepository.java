package com.desafiobackendpicpay.repositories;

import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    static void save(com.desafiobackendpicpay.domain.transaction.Transaction newTransaction) {
    }
}
