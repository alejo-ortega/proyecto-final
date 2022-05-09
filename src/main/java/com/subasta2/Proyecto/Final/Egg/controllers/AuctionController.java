package com.subasta2.Proyecto.Final.Egg.controllers;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.entities.Objects;
import com.subasta2.Proyecto.Final.Egg.enums.Category;
import com.subasta2.Proyecto.Final.Egg.services.AuctionService;
import com.subasta2.Proyecto.Final.Egg.services.ObjectsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    private final ObjectController objectController;
    private final AuctionService auctionService;
    private final ObjectsService objectsService;

    @Autowired
    public AuctionController(AuctionService auctionService, ObjectController objectController, ObjectsService objectsService) {
        this.auctionService = auctionService;
        this.objectController = objectController;
        this.objectsService = objectsService;
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
        return "auction/list-auctions";
    }

    /**
     * Shows a form that allows the seller to create a new auction. If the
     * auction already exist, the method allows you to edit its values.
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
    public String showForm(ModelMap categoryModel, ModelMap stateModel, ModelMap objectModel, ModelMap auctionModel, @RequestParam(required = false) String id) {

        try {
            objectController.categoryList(categoryModel);
            objectController.stateList(stateModel);
        } catch (Exception e) {
        }
        return "auction/auction-form";
    }

    /**
     * This method persist the auction information into the DataBase
     *
     * @param auction
     * @param model
     * @return
     */
    @PostMapping("/form")
    public String processForm(@ModelAttribute Auction auction, ModelMap model) {
        try {
            auctionService.save(auction);
        } catch (Exception e) {
            model.addAttribute("error " + e.getMessage());
            return "redirect:";
        }
        return "redirect:/auction";
    }

    @GetMapping("/{id}")
    public String auctionDetail(ModelMap model, @PathVariable("id") String id) {
        try {
            Auction auction = auctionService.showOne(id);
            model.addAttribute("auction", auction);
        } catch (Exception exception) {
            // TODO! handle error
        }

        return "auction/auction-detail";
    }
}
