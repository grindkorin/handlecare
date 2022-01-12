package com.example.handlecare.controller.deliverController;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("/deliver/personal")
public class PersonalController {

    final
    DeliverServiceImpl deliverService;

    public PersonalController(DeliverServiceImpl deliverService) {
        this.deliverService = deliverService;
    }

    @GetMapping("/deliver/personal")
    public String personal(ModelMap model) {
        Deliver deliver = deliverService.getById(1); //todo session
        model.put("deliver", deliver);
        return "/deliver/personal";
    }

    @PostMapping("/updateData")
    public String deliverData(@ModelAttribute Deliver deliver,
                              Model model , RedirectAttributes redirectAttributes) {
        model.addAttribute("deliver", deliverService.updateDeliver(deliver));
        redirectAttributes.addFlashAttribute("message", "Изменения сохранены");
        return "redirect:deliver/personal";
    }
}
