
package com.subasta2.Proyecto.Final.Egg.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auction {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    
    private Object object;
    
    @OneToOne
    private Customer customerSeller;
    
    @OneToOne
    private Customer customerBuyer;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate auctionDate;
    
    private Double minimumValue;
    private Double bid;
    
    @OneToMany
    private List<Customer> customerList;
}
