package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecondMailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public String buildSecondTrelloCardEmail(String message) {
        List<String> bulletPoints = new ArrayList<>();
        bulletPoints.add("We guarantie salvation.");
        bulletPoints.add("We can take care of your money.");
        bulletPoints.add("And everything else.");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "htpp://localhost:8888/crud");
        context.setVariable("button", "Change your life!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", "Company: We are not a Cult! " +
                "Address: 674 Palo Alto, " +
                "phone: 896973249, " +
                "email: notacult@notacult.com");
        context.setVariable("goodbye_message", "Be blessed!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", bulletPoints);
        return templateEngine.process("mail/not-a-cult-mail", context);
    }
}