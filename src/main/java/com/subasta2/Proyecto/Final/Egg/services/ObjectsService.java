package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import com.subasta2.Proyecto.Final.Egg.entities.Objects;
import com.subasta2.Proyecto.Final.Egg.exceptions.ExceptionService;
import com.subasta2.Proyecto.Final.Egg.interfaces.ServiceInterface;
import com.subasta2.Proyecto.Final.Egg.repositories.ObjectsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {

    private ObjectsRepository objectsRepository;
    private CustomerService customerService;

    @Autowired
    public ObjectsService(ObjectsRepository objectsRepository, CustomerService customerService) {
        this.objectsRepository = objectsRepository;
        this.customerService = customerService;
    }

    @Transactional(rollbackOn = {Exception.class})
    public void create(Objects object) throws ExceptionService {
        validate(object);
        object.setActive(true);
        objectsRepository.save(object);
    }

    public Objects findById(String id) throws ExceptionService {
        if (id.trim().isEmpty()) {
            throw new ExceptionService("el id esta vacio");
        }
        Optional<Objects> objectId = objectsRepository.findById(id);
        if (objectId.isPresent()) {
            Objects object = objectId.get();
            return object;
        } else {
            throw new ExceptionService("no se encontro un objeto con ese id");
        }
    }

    @Transactional(rollbackOn = {Exception.class})
    public void edit(Objects object) throws ExceptionService {
        objectsRepository.save(object);
    }

    public List<Objects> showList() {
        return objectsRepository.findAll();
    }

    public Objects showObject(String id) throws ExceptionService {
        return findById(id);
    }

    @Transactional(rollbackOn = {Exception.class})
    public void activate(String id) throws ExceptionService {
        Objects object = findById(id);
        object.setActive(true);
    }

    @Transactional(rollbackOn = {Exception.class})
    public void deactivate(String id) throws ExceptionService {
        Objects object = findById(id);
        object.setActive(false);
    }

    @Transactional(rollbackOn = {Exception.class})
    public void changeCustomer(Objects object, Customer customer) throws ExceptionService, Exception {
        object.setCustomer(customerService.findById(customer.getId()));
    }

    public void validate(Objects object) throws ExceptionService {
        if (object.getName().isEmpty() || object.getName() == null) {
            throw new ExceptionService("El nombre esta vacio");
        }
        if (object.getDescription().isEmpty() || object.getName() == null) {
            throw new ExceptionService("La descripcion esta vacia");
        }
        if (object.getName().isEmpty() || object.getName() == null) {
            throw new ExceptionService("El nombre esta vacio");
        }
        if (object.getDescription().trim().isEmpty() || object.getDescription() == null) {
            throw new ExceptionService("La descripcion esta vacia");
        }
        if (object.getInitialValue().toString().trim().isEmpty() || object.getInitialValue() == null) {
            throw new ExceptionService("El valor inicial esta vacio");
        }
        if (object.getInitialValue() == null) {
            throw new ExceptionService("El valor inicial esta vacio");
        }
        if (object.getCategory() == null) {
            throw new ExceptionService("Tiene que indicar la categoria");
        }
        if (object.getState() == null) {
            throw new ExceptionService("Tiene que indicar el estado");
        }
    }
}
