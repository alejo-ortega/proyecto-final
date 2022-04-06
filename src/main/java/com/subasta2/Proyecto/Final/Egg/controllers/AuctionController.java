
package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.services.AuctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;
    
    @Autowired
    public AuctionController(AuctionService auctionService){
        this.auctionService = auctionService;
    }
    
    @GetMapping
    public String showList(ModelMap model){
        List<Auction> auction = auctionService.showList() ;
        model.addAttribute("auctions", auction);
        return "auction/list-auctions";
    }
    
    @GetMapping("/form")
    public String showForm(ModelMap model, @RequestParam(required = false) String id) throws Exception{
        if(id == null){
            model.addAttribute("auction", new Auction());
        } else{
            Auction auction = auctionService.showOne(id);
            model.addAttribute("auction", auction);
            //auctionService.edit(auction);?????
        }
        return "auction/form";
    }
    
    @PostMapping("/form")
    public String processForm(@ModelAttribute Auction auction, ModelMap model){
        System.out.println("auction ="+ auction );
        try{
            auctionService.save(auction);
        }catch(Exception e){
            model.addAttribute("error "+ e.getMessage());
            return "redirect:";
        }
        return "redirect:/auction";
    }
}
