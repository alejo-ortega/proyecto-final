package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import com.subasta2.Proyecto.Final.Egg.entities.Objects;
import com.subasta2.Proyecto.Final.Egg.enums.Category;
import com.subasta2.Proyecto.Final.Egg.services.AuctionService;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/auction")
//@PreAuthorize("hasAnyRole('ROLE_USER')")
public class AuctionController {

    private final ObjectController objectController;
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService, ObjectController objectController) {
        this.auctionService = auctionService;
        this.objectController = objectController;
    }

    /**
     * Sends the auction list to the HTML by a model, so it can be shown on the
     * FrontEnd.
     *
     * @param model
     * @return
     */
    @GetMapping
    public String showList(ModelMap model) {
        List<Auction> auctions = auctionService.showList();
        model.addAttribute("auctions", auctions);
        Category[] categories = Category.values();
        return "/index";
    }

    /**
     * Shows a Form tha allows the seller to create a new auction.
     *
     * @param categoryModel
     * @param stateModel
     * @param model
     * @param objectModel
     * @param auctionModel
     * @param id
     * @return
     */
    @GetMapping("/form")
    public String showForm(ModelMap category, ModelMap state, ModelMap objectModel) {

        try {
            objectController.categoryList(category);
            objectController.stateList(state);
            objectModel.addAttribute("objects", new Objects());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "auction/auction-form.html";
    }

    /**
     * This method persist the auction information into the DataBase
     *
     * @param object
     * @param auction
     * @param model
     * @return
     */
    @PostMapping("/form")
    public String processForm(@ModelAttribute Objects objects, ModelMap model,@RequestParam Date auctionDate) {
        try {          
                               
            objectController.objectService.saveAdd(objects,auctionDate);    
            return "redirect:/auction";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.put("error", e.getMessage());
            return "/auction/auction-form";
        }
        
    }
}
