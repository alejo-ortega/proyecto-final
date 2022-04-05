
package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Picture;
import com.subasta2.Proyecto.Final.Egg.repositories.PictureRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService {
    
    private final PictureRepository pictureRepository;
    
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    
    @Transactional (rollbackOn = {Exception.class})
    public Picture save(MultipartFile file) throws Exception{
        
        if (file != null) {
            Picture picture = new Picture();
            picture.setMime(file.getContentType());
            picture.setName(file.getName());
            picture.setContenido(file.getBytes());
            return pictureRepository.save(picture);
        }else{
            return null;
        }                
    }
    
    @Transactional
    public Picture edit(String idPicture, MultipartFile file) throws Exception{
        
        if (file != null) {
            Picture picture = new Picture();
            if (idPicture != null) {
                Optional<Picture> answer = pictureRepository.findById(idPicture);
                if (answer.isPresent()) {
                    picture = answer.get();
                }
            }
            picture.setMime(file.getContentType());
            picture.setName(file.getName());
            picture.setContenido(file.getBytes());
            
            return pictureRepository.save(picture);
        }else{
            return null;
        }        
    }
    
    
    
}
