package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.enums.Category;
import com.subasta2.Proyecto.Final.Egg.enums.State;
import com.subasta2.Proyecto.Final.Egg.interfaces.ServiceInterface;
import com.subasta2.Proyecto.Final.Egg.repositories.AuctionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService implements ServiceInterface<Auction> {

    public AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void save(Auction auction) throws Exception {
        validate(auction);
        auctionRepository.save(auction);
    }

    @Override
    public void validate(Auction auction) throws Exception {
        if (auction.getObjects() == null) {
            throw new Exception("Debe seleccionar un objeto");
        }
        if (auction.getMinimumValue() == null) {
            throw new Exception("Debe seleccionar un precio mínimo");
        }
    }

    @Override
    public List<Auction> showList() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction showOne(String id) throws Exception {
        return auctionRepository.findById(id).orElse(null);
    }

    @Override
    public void deactivate(Auction auction) throws Exception {
        auction.setActive(Boolean.FALSE);
        save(auction);
    }

    @Override
    public void activate(Auction auction) throws Exception {
        auction.setActive(Boolean.TRUE);
        save(auction);
    }

    public void edit(Auction auction) throws Exception {
        validate(auction);
        auctionRepository.save(auction);
    }

    public List<String> categoryList() {
        List<String> categories = new ArrayList();
        for (Category category : Category.values()) {
            categories.add(category.getValor());
        }
        return categories;
    }

    public List<String> stateList() {
        List<String> states = new ArrayList();
        for (State state : State.values()) {
            states.add(state.getValor());
        }
        return states;
    }

    public List<Auction> findByName(String name) throws Exception {
        List<Auction> auctions = auctionRepository.findByObjectsName(name);
        return auctions;
    }

    public List<Auction> findByCategory(String category) throws Exception {
        List<Auction> auctions = auctionRepository.findByObjectsCategory(category);
        return auctions;
    }

    public List<Auction> findByState(String state) throws Exception {
        List<Auction> auctions = auctionRepository.findByObjectsState(state);
        return auctions;
    }

    public List<Auction> findByValue(Double valueMin, Double valueMax) throws Exception {
        List<Auction> auctions = auctionRepository.findByValue(valueMin, valueMax);
        return auctions;
    }

    public List<Auction> findByAll(String name, String category, String state, String valueMin, String valueMax) throws Exception {
        if (valueMin.trim().isEmpty() || valueMin == null) {
            System.out.println("precio minimo esta vacio");
            valueMin = "0.00";
        }
        if (valueMax.trim().isEmpty() || valueMax == null) {
            System.out.println("precio maximo esta vacio");
            valueMax = "1000000000000.00";
        }
        Double valueMin2 = Double.parseDouble(valueMin);
        Double valueMax2 = Double.parseDouble(valueMax);
        List<Auction> auctions1 = new ArrayList();
        List<Auction> auctions2 = new ArrayList();
        List<Auction> auctions3 = new ArrayList();
        List<Auction> listByName = auctionRepository.findByObjectsName(name);
        List<Auction> listByCategory = auctionRepository.findByObjectsCategory(category);
        List<Auction> listByState = auctionRepository.findByObjectsState(state);
        List<Auction> listByValue = auctionRepository.findByValue(valueMin2, valueMax2);
        for (Auction auction : listByCategory) {
            if (listByName.contains(auction)) {
                auctions1.add(auction);
            }
        }
        for (Auction auction : listByValue) {
            if (listByState.contains(auction)) {
                auctions2.add(auction);
            }
        }
        for (Auction auction : auctions1) {
            if (auctions2.contains(auction)) {
                auctions3.add(auction);
            }
        }

        return auctions3;
    }
}
