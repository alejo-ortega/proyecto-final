
package com.subasta2.Proyecto.Final.Egg.repositories;

import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
    
    public Customer findByNameContaining(String name);
    

    public Customer findByEmailContaining(String email);

}
