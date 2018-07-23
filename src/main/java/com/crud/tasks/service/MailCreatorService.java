package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;


    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "htpp://localhost:8888/crud");
        context.setVariable("button", "Visit website!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", "Company: We are not a Cult! " +
                "Address: Puerto Rico, " +
                "phone: 123456789, " +
                "email: bleble@blevle.com");
        context.setVariable("goodbye_message", "See you!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}