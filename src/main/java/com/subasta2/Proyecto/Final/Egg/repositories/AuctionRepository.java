package com.subasta2.Proyecto.Final.Egg.repositories;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.enums.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

    @Query("SELECT a FROM Auction a WHERE a.objects.name LIKE %:name%")
    public List<Auction> findByObjectsName(@Param("name") String name);
    
//    public List<Auction> findByObjectsName(String name);

    public List<Auction> findByObjectsCategory(String category);
    
}
