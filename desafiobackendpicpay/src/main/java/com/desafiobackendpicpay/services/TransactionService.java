package com.desafiobackendpicpay.services;

import com.desafiobackendpicpay.domain.transaction.Transaction;
import com.desafiobackendpicpay.domain.user.User;
import com.desafiobackendpicpay.dtos.TransactionDTO;
import com.desafiobackendpicpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction(sender, transaction.value());

        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        // Atualiza os saldos
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        // Cria e salva a transação
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        TransactionRepository.save(newTransaction);

        // Salva os usuários com saldo atualizado
        userService.saveUser(sender);
        userService.saveUser(receiver);

        // Envia notificações
        notificationService.sendNotification(sender, "Transação realizada com sucesso");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(
                    "https://util.devi.tools/api/v2/authorize", Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                Object message = response.getBody().get("message");
                return "Autorizado".equalsIgnoreCase(message.toString());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Em produção, use um logger adequado
        }
        return false;
    }
}

