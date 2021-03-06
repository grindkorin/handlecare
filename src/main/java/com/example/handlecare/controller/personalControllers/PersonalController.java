package com.example.handlecare.controller.personalControllers;

import com.example.handlecare.entity.Recipient;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("/personal/personal")
public class PersonalController {


    final
    RecipientServiceImpl recipientService;

    public PersonalController(RecipientServiceImpl recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping("/personal/personal")
    public String personal(Authentication authentication, ModelMap model) {
        Recipient recipient = recipientService.findByLogin(authentication.getName());
        model.put("recipient", recipient);
        return "/personal/personal";
    }

    @PostMapping("/updateRecipient")
    public String recipientData(@ModelAttribute Recipient recipient,
                              Model model , RedirectAttributes redirectAttributes) {
        model.addAttribute("recipient", recipientService.updateRecipient(recipient));
        redirectAttributes.addFlashAttribute("message", "Изменения сохранены");
        return "redirect:personal/personal";
    }
}
