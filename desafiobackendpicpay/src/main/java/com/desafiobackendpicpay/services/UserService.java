package com.desafiobackendpicpay.services;

import com.desafiobackendpicpay.domain.user.User;
import com.desafiobackendpicpay.domain.user.UserType;
import com.desafiobackendpicpay.dtos.UserDTO;
import com.desafiobackendpicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        // Aqui a lógica correta: Lojista (MERCHANT) não pode enviar dinheiro
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do Tipo Lojista não está autorizado a realizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);

    }
}

