package com.subasta2.Proyecto.Final.Egg.repositories;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

    @Query("SELECT a FROM Auction a WHERE a.objects.name LIKE %:name%")
    public List<Auction> findByObjectsName(@Param("name") String name);

    @Query("SELECT a FROM Auction a WHERE a.objects.category LIKE %:category%")
    public List<Auction> findByObjectsCategory(@Param("category") String category);

    @Query("SELECT a FROM Auction a WHERE a.objects.state LIKE %:state%")
    public List<Auction> findByObjectsState(@Param("state") String state);

    @Query("SELECT a FROM Auction a WHERE a.minimumValue BETWEEN :valueMin and :valueMax")
    public List<Auction> findByValue(@Param("valueMin") Double valueMin, @Param("valueMax") Double valueMax);
}
