package com.salesianos.triana.DoradoMoises_Ready2Ref.util;

import com.resend.services.emails.model.Email;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
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
        Email from = new Email("dorado.gumoi24@triana.salesianos.edu");
        Email recipient = new Email(to);
        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, recipient, content);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("Response Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

        } catch (IOException ex) {
            throw new IOException("Error al enviar email con SendGrid", ex);
        }
    }
}