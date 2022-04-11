
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
    
    /**
     * Sends the auction list to the HTML by a model, so it can be shown on the
     * FrontEnd.
     * @param model
     * @return 
     */
    @GetMapping
    public String showList(ModelMap model){
        List<Auction> auctions = auctionService.showList() ;
        model.addAttribute("auctions", auctions);
        return "auction/list-auctions";
    }
    
    /**
     * Shows a Form tha allows the seller to create a new auction. If the auction
     * already exist, the method allows you to edit its values.
     * @param model
     * @param id
     * @return
     * @throws Exception 
     */
    @GetMapping("/form")
    public String showForm(ModelMap model, @RequestParam(required = false) String id){
        
        try {
            if(id == null){
            model.addAttribute("auction", new Auction());
        } else{
            Auction auction = auctionService.showOne(id);
            model.addAttribute("auction", auction);
            auctionService.edit(auction);
        }
        } catch (Exception e) {
        }
        return "auction/form";
    }
    
    /**
     * This method persist the auction information into the DataBase
     * @param auction
     * @param model
     * @return 
     */
    @PostMapping("/form")
    public String processForm(@ModelAttribute Auction auction, ModelMap model){
        try{
            auctionService.save(auction);
        }catch(Exception e){
            model.addAttribute("error "+ e.getMessage());
            return "redirect:";
        }
        return "redirect:/auction";
    }
}
