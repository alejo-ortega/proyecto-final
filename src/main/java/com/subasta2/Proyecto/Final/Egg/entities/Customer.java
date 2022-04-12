
package com.subasta2.Proyecto.Final.Egg.entities;

import com.subasta2.Proyecto.Final.Egg.enums.Role;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    
    @EqualsAndHashCode.Include
    private String Dni;
    
    private String name;
    private String lastName;     
    private String email;    
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
     
    @OneToOne
    private Picture picture;
    
    private Boolean verificate;
    
    @OneToMany
    private List<Objects> objectPush;
    
    @OneToMany
    private List<Objects> objectHistorySold;
    
    private Double balance;
    private Boolean active;   
    
}
