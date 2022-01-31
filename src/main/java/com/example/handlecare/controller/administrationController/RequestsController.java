package com.example.handlecare.controller.administrationController;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.example.handlecare.entity.enums.Status.ACTIVE;
import static com.example.handlecare.entity.enums.Status.UNCHECKED;

@Controller("administration/requests")
public class RequestsController {

    final
    RecipientServiceImpl recipientService;
    final
    DeliverServiceImpl deliverService;

    public RequestsController(RecipientServiceImpl recipientService, DeliverServiceImpl deliverService) {
        this.recipientService = recipientService;
        this.deliverService = deliverService;
    }

    @GetMapping("/administration/requests")
    public String allDelivers(ModelMap model) {
        List<Deliver> delivers = deliverService.findAllByStatus(UNCHECKED);
        List<Recipient> recipients = recipientService.findAllByStatus(UNCHECKED);
        model.put("delivers", delivers);
        model.put("recipients", recipients);
        model.put("statuses", Status.values());
        return "/administration/requests";
    }

    @PostMapping("/changeDeliverStatus/{id}")
    public String changeDeliverStatus(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Deliver deliver = deliverService.getById(id);
        deliver.setStatus(ACTIVE);
        deliverService.save(deliver);
        return "redirect:/administration/requests";
    }

    @PostMapping("/changeRecipientStatus/{id}")
    public String changeRecipientStatus(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Recipient recipient = recipientService.getById(id);
        recipient.setStatus(ACTIVE);
        recipientService.save(recipient);
        return "redirect:/administration/requests";
    }
}
