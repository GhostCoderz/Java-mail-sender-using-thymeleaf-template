package com.ghostcoderz.javamailsenderusingthymeleaftemplate.service;

import com.ghostcoderz.javamailsenderusingthymeleaftemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired private final JavaMailSender javaMailSender;

    @Autowired private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(User user) throws MessagingException {

        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getName());
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        javaMailSender.send(mimeMessage);

    }

}
