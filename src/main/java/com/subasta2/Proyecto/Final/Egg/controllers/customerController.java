
package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class customerController {
    private final customerService CustomerService;
    
    @Autowired
    public CustomerController(customerService CustomerService){
        this.CustomerService = CustomerService;
    }
    
    @GetMapping
    public String listarCustomer(ModelMap model){
        List<Customer> customers = CustomerService.listarCustomer();
        model.addAttribute("Customer" customer);
    }
}
