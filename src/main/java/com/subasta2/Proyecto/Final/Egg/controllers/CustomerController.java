package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import com.subasta2.Proyecto.Final.Egg.services.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String showList(ModelMap model) {
        
        try{
            List<Customer> customers = customerService.showList();
            model.addAttribute("customers", customers);
            return "customer/list-customers";
        }catch(Exception e){
            model.put("error", e.getMessage());
            return "/customers/list-customers";
        }
    }

    @GetMapping("/edit-profile")
    public String activate(@RequestParam String id, ModelMap model) throws Exception {
        
        try{
            model.addAttribute("customer", customerService.findById(id));
        }catch (Exception e){
            model.put("error", e.getMessage());
            return "/customers/list-customers";
        }
        
        return "customer/list-customers";
    }

    @PostMapping("/edit-profile")
    public String activatePost(@RequestParam String id, ModelMap model) {

        try {
            customerService.onOff(id);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "/customers/list-customers";
        }

        return "redirect:/customer/list-customers";
    }
    
    @GetMapping("/login-form")
    public String ShowLogin(ModelMap model, @RequestParam(required = false) String id) throws Exception{
        try{
            if(id != null){
                Customer customer = customerService.findById(id);
                model.addAttribute("customer", customer);
            }
            
        }catch(Exception e){
            model.put("error", e.getMessage());
            return "/login-form";
        }
        
        return "/login-form";
    } // formulario para el inicio de sesión
    
    @PostMapping("/login-form")
    public String processLogin(@ModelAttribute Customer customer, ModelMap model)throws Exception{
        System.out.println("Customer = "+ customer);
        try{
            customerService.login(customer);
        }catch(Exception e){
            model.addAttribute("error "+ e.getMessage());
            return "login-form";
        }
        return "login-form";
    }
    
    @GetMapping("/form")
    public String showForm(ModelMap model, @RequestParam(required = false) String id) throws Exception {
        try {
            if (id == null) {
                model.addAttribute("customer", new Customer());
            } else {
                Customer customer = customerService.findById(id);
                model.addAttribute("customer", customer);
            }
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "/customers/list-customers";
        }
        return "customer/form";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute Customer customer, ModelMap model, MultipartFile file){
        System.out.println("Customer ="+ customer );
        try{
            customerService.register(customer,file);
        }catch(Exception e){
            model.addAttribute("error "+ e.getMessage());
            return "redirect:";
        }
        return "redirect:/customer";
    }

//    Dar de baja
//Validar usuario antes de la compra (verificar que sea el usuario a través de mail y contraseña)
//Validar si el usuario está en estado de alta al loguearse
//Puntuar a otro usuario
}
