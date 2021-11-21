package com.ghostcoderz.javamailsenderusingthymeleaftemplate.controller;

import com.ghostcoderz.javamailsenderusingthymeleaftemplate.entity.User;
import com.ghostcoderz.javamailsenderusingthymeleaftemplate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
public class EmailSender {

    @Autowired EmailService emailService;

    @RequestMapping("/email/send")
    public String sendMessage() throws MessagingException {
        User user = new User();
        user.setName("<name>");
        user.setEmail("<user-email>");
        user.setUsername("<username>");
        this.emailService.sendEmail(user);
        return "Email sent to the below user : \n " + user;
    }

}
