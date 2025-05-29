package com.desafiobackendpicpay.services;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.desafiobackendpicpay.domain.user.User;
import com.desafiobackendpicpay.dtos.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDto notificationRequest = new NotificationDto(email, message);


        //ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        //if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            //System.out.println("erro ao enviar Notificação");
            //throw new Exception("Serviço de Notificação está fora do ar");
       // }

        System.out.print("Notificação enviada para o usuario");

    }
}
