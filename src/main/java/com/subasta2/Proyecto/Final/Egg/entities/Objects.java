package com.subasta2.Proyecto.Final.Egg.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
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
    private String id;

    private String name;

    @OneToMany
    private List<Picture> picture;
    private String description;
    private Double initialValue;
    private Double finalValue;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate soldDate;

    private String state;

    private String category;

    private Boolean active;
    private Boolean sold;

    @ManyToOne
    private Customer customer;
    @OneToOne
    private Auction auction;

}
