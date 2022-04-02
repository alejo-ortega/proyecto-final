package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.interfaces.ServiceInterface;
import com.subasta2.Proyecto.Final.Egg.repositories.AuctionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService implements ServiceInterface {
    
    public AuctionRepository auctionRepository;
    
    @Autowired
    public AuctionService (AuctionRepository auctionRepository){
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void save(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void validate(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List showList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object showOne(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
