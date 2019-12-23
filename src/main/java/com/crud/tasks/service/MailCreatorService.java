package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context contex = new Context();
        contex.setVariable("message", message);
        contex.setVariable("tasks_url", "http://localhost:80/crud");
        contex.setVariable("button", "Visit website");
        contex.setVariable("admin_name", adminConfig.getAdminName());
        contex.setVariable("goodbye", "See ya later!");
        contex.setVariable("admin_company", adminConfig.getAdminCompany());
        contex.setVariable("show_button", false);
        contex.setVariable("is_friend", true);
        contex.setVariable("admin_config", adminConfig);
        contex.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", contex);
    }

    public String buildTrelloTasksQuantityOnceADay(String message) {
        long size = taskRepository.count();

        String taskOrTasks = (size<=1) ? " task." : " tasks.";

        List<String> functionality = new ArrayList<>();
        functionality.add("Here is your onca a day e-mail:");
        functionality.add("You currently have: ");
        functionality.add(size + taskOrTasks);

        Context contex = new Context();
        contex.setVariable("message", message);
        contex.setVariable("tasks_url", "http://localhost:80/crud");
        contex.setVariable("button", "Visit website");
        contex.setVariable("admin_name", adminConfig.getAdminName());
        contex.setVariable("goodbye", "That's all for today!");
        contex.setVariable("admin_company", adminConfig.getAdminCompany());
        contex.setVariable("show_button", false);
        contex.setVariable("is_friend", true);
        contex.setVariable("admin_config", adminConfig);
        contex.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/second-card-mail.html", contex);
    }
}
