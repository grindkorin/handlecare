package com.example.handlecare.controller.administrationController;


import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("administration/allDelivers")
public class AllDeliversController {

    final
    DeliverServiceImpl deliverService;



    public AllDeliversController(DeliverServiceImpl deliverService) {
        this.deliverService = deliverService;
    }

    @GetMapping("/administration/allDelivers")
    public String allDelivers(ModelMap model) {
        List<Deliver> delivers = deliverService.findAll();
        delivers.removeIf(d -> d.getRole() == Roles.ADMIN);
        model.put("delivers", delivers);
        return "/administration/allDelivers";
    }

}
