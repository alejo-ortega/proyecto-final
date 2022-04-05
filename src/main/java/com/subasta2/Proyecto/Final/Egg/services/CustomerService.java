
package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import com.subasta2.Proyecto.Final.Egg.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class CustomerService implements UserDetailsService{
    
    private CustomerRepository customerrepository;
    
    @Autowired    
    public CustomerService(CustomerRepository customerrepository) {
        this.customerrepository = customerrepository;
    }
    
    @Transactional (rollbackOn = {Exception.class})
    public void register(Customer customer) throws Exception{  
        setOn(customer);
        validation(customer);
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        customerrepository.save(customer);        
    }
    
    @Transactional (rollbackOn = {Exception.class})
    public void edit(Customer customer) throws Exception{      
        validation(customer);
        customerrepository.save(customer);
    }    
    
    @Transactional (rollbackOn = {Exception.class})
    public void addBalance(String id, Double balance) throws Exception{
        Customer customer = findById(id);
        customer.setBalance(balance);
        customerrepository.save(customer);
    }
    
    @Transactional
    public Customer findById(String id) throws Exception {
        return customerrepository.findById(id).orElseThrow(() -> new Exception("No se encontro el usuario"));
    }
        
    public List<Customer> showList(){
        return customerrepository.findAll();
    }
        
    public Customer showObject(String id) throws Exception{
        return findById(id);
    }

    private void validation(Customer customer) throws Exception {        
        if (customer.getName().isEmpty() || customer.getName().equals(" ") || customer.getName() == null || customer.getName().length()<2) {
            throw new Exception("El nombre ingresado es invalido");
        }        
        if (customer.getLastName().isEmpty() || customer.getLastName().equals(" ") || customer.getLastName() == null || customer.getLastName().length()<2) {
            throw new Exception("El apellido ingreado es invalido");
        }        
        if (customer.getDni().length() < 8 || customer.getDni().length() > 10 || customer.getDni().isEmpty() || customer.getDni().equals(" ") || customer.getDni() == null) {
            throw new Exception("El DNI ingreado es invalido");
        }        
        if (customer.getEmail().isEmpty() || customer.getEmail().contains(" ") || customer.getEmail() == null) {
            throw new Exception("El email ingresado es invalido");
        }        
        if (customer.getPassword().isEmpty() || customer.getPassword().contains(" ") || customer.getPassword() == null) {
            throw new Exception("La contraseña ingresada es invalida");
        }                
    }
    
    public void checkActive(Customer customer) throws Exception{
        if (customer.getActive().equals(false)) {
            throw new Exception("El usuario seleccionado esta dado de baja");
        }
    }
    
    @Transactional(rollbackOn = {Exception.class})
    public void activate(String id) throws Exception {
        Customer customer = findById(id);
        customer.setActive(true);
    }
    
    @Transactional(rollbackOn = {Exception.class})
    public void deactivate(String id) throws Exception {
        Customer customer = findById(id);
        customer.setActive(false);
    }
    
     @Transactional(rollbackOn = {Exception.class})
    public void onOff(String id) throws Exception{
        Customer customer = findById(id);
         if (customer.getActive() == null || customer.getActive() == false) {
            activate(id);
         }else{
            deactivate(id);            
         } 
    }

    private void setOn(Customer customer) {
        if (customer.getActive() == null || customer.getActive().equals(false)) {
            customer.setActive(true);
        }
    }
    
    @Transactional
    public void setStars(Customer customer,Integer stars){        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Customer customer = customerrepository.findByEmailContaining(email);
        
        if (customer == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        
        List<GrantedAuthority> permissions = new ArrayList<>();
        GrantedAuthority rolePermissions = new SimpleGrantedAuthority("ROLE_"+customer.getRole().toString());
        permissions.add(rolePermissions);
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("customersession", customer);
        
        return new User(customer.getEmail(),customer.getPassword(),permissions);        
    }
}
