package com.salesianos.triana.DoradoMoises_Ready2Ref.util;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log
@Service
public class SendGridMailSender {

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;


    @Async
    public void sendMail(String to, String subject, String message) throws IOException {
        log.info("Enviando email a " + to + " con asunto " + subject);

    }
}
