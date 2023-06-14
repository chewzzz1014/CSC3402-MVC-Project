package com.csc3402.dbproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("display/{customer_id}")
    public String getCart(@PathVariable("customer_id") long id, Model model){
        List<StaffProject> staffProject = (List<StaffProject>) staffProjectRepository.findStaffProjectByStaffId((int) id);
        
        return "cart";
    }

    // add to cart

}
