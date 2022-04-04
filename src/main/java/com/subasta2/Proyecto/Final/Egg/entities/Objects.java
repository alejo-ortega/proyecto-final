package com.subasta2.Proyecto.Final.Egg.entities;

import com.subasta2.Proyecto.Final.Egg.enums.Category;
import com.subasta2.Proyecto.Final.Egg.enums.State;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

public class Objects {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;

    private String name;

    @OneToMany
    private List<Picture> picture;

    private String description;
    private Double initialValue;
    private Double finalValue;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate soldDate;
    
    @Enumerated(EnumType.STRING)
    private State state;
    
    @Enumerated(EnumType.STRING)
     private Category category;
    
    private Boolean active;
    private Boolean sold;

    @ManyToOne
    private Customer customer;
    @OneToOne
    private Auction auction;

}
