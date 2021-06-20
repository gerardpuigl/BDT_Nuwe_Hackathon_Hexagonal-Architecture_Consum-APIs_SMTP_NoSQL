package io.nuwe.hackatonMWC.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.domain.User;



@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

	@Override
    @Async
    public void sendWelcomeEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // set email properties/body
            message.setTo(user.getEmail());
            message.setSubject("Welcome to Hackathon MWC");
            message.setText("Welcome to  Hackathon MWC \n\nThanks for signing up!");

            // send email
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
    

}