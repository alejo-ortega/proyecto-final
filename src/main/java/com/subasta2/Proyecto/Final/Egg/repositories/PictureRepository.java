
package com.subasta2.Proyecto.Final.Egg.repositories;

import com.subasta2.Proyecto.Final.Egg.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,String>{
    
}
