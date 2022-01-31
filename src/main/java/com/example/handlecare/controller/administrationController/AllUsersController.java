package com.example.handlecare.controller.administrationController;

import com.example.handlecare.entity.Recipient;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("administration/allUsers")
public class AllUsersController {

    final
    RecipientServiceImpl recipientService;

    public AllUsersController(RecipientServiceImpl recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping("/administration/allUsers")
    public String allDelivers(ModelMap model) {
        List<Recipient> recipients = recipientService.findAll();
        //TODO refactor to dto
        model.put("recipients", recipients);
        return "/administration/allUsers";
    }
}
