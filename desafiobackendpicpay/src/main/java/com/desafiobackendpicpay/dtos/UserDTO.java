package com.desafiobackendpicpay.dtos;

import com.desafiobackendpicpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String fistName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
