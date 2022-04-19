package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.enums.Category;
import com.subasta2.Proyecto.Final.Egg.enums.State;
import com.subasta2.Proyecto.Final.Egg.services.AuctionService;
import com.subasta2.Proyecto.Final.Egg.services.CustomerService;
import com.subasta2.Proyecto.Final.Egg.services.ObjectsService;
import com.subasta2.Proyecto.Final.Egg.services.PictureService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {
    
    private CustomerService cs;
    private AuctionService as;
    private ObjectsService os;
    private PictureService ps;
    
    @Autowired
    public IndexController(CustomerService cs, AuctionService as, ObjectsService os, PictureService ps) {
        this.cs = cs;
        this.as = as;
        this.os = os;
        this.ps = ps;
    }
    
    @GetMapping
    public String index(ModelMap model){
        try {
            List<Auction> auctionList = as.showList();
            model.addAttribute("auctionList", auctionList);
            List<String> categories = new ArrayList();
            for (Category category : Category.values()) {
                categories.add(category.getValor());
            }
            model.addAttribute("categories", categories);
            List<String> states = new ArrayList();
            for (State state : State.values()) {
                states.add(state.getValor());
            }
            model.addAttribute("states", states);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage().toString());
            return "index";
        }
        return "index";
    }
    
    
}
