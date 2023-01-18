package com.placideh.ticketingapi.mails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class EmailSenderService {

    @Value("${from-email}")
    private String fromEmail;
    @Autowired
    private JavaMailSender mailSender;
    public void sendCommunicationEmail(String toEmail,String body,String subject) throws Exception{
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        message.setReplyTo(toEmail);
        message.setSentDate(new Date());
        mailSender.send(message);
        log.info("email sent");
    }
}
