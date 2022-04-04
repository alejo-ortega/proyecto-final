package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.interfaces.ServiceInterface;
import com.subasta2.Proyecto.Final.Egg.repositories.AuctionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService implements ServiceInterface<Auction> {
    
    public AuctionRepository auctionRepository;
    
    @Autowired
    public AuctionService (AuctionRepository auctionRepository){
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void save(Auction auction) throws Exception {
        validate(auction);
        auctionRepository.save(auction);
    }

    @Override
    public void validate(Auction auction) throws Exception {
        if (auction.getObjects()==null) {
            throw new Exception ("Debe seleccionar un objeto");
        }
        if (auction.getMinimumValue()== null) {
            throw new Exception ("Debe seleccionar un precio mínimo");
        }
    }

    @Override
    public List<Auction> showList() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction showOne(String id) {
        return auctionRepository.findById(id).orElse(null);
    }

    @Override
    public void deactivate(Auction auction) throws Exception{
        auction.setActive(Boolean.FALSE);
        save(auction);
    }

    @Override
    public void activate(Auction auction) throws Exception{
        auction.setActive(Boolean.TRUE);
        save(auction);
    }
  
}
