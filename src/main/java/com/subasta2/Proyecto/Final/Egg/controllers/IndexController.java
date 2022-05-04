package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.services.AuctionService;
import com.subasta2.Proyecto.Final.Egg.services.CustomerService;
import com.subasta2.Proyecto.Final.Egg.services.ObjectsService;
import com.subasta2.Proyecto.Final.Egg.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(ModelMap model) {
        try {
            model.addAttribute("auctionList", as.showList());
            model.addAttribute("categories", as.categoryList());
            model.addAttribute("states", as.stateList());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage().toString());
            return "index";
        }
        return "index";
    }

    @PostMapping("/")
    public String find(@RequestParam(required = false) String name, @RequestParam(required = false) String category,
            @RequestParam(required = false) String state, @RequestParam(required = false) String valueMin, @RequestParam(required = false) String valueMax, ModelMap model) {
        try {
            model.addAttribute("auctionList", as.findByAll(name, category, state, valueMin, valueMax));
            model.addAttribute("categories", as.categoryList());
            model.addAttribute("states", as.stateList());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.put("categories", as.categoryList());
            model.put("states", as.stateList());
            return "index";
        }
        return "index";
    }

}
