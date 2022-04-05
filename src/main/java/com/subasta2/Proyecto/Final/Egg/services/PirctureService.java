
package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PirctureService {
    
    private final PictureRepository pictureRepository;
    
    @Autowired
    public PirctureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    
    public void save(){
        
    }

    
    
}
