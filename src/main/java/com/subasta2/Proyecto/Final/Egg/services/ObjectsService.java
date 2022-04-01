package com.subasta2.Proyecto.Final.Egg.services;

import com.subasta2.Proyecto.Final.Egg.entities.Auction;
import com.subasta2.Proyecto.Final.Egg.entities.Customer;
import com.subasta2.Proyecto.Final.Egg.entities.Objects;
import com.subasta2.Proyecto.Final.Egg.exceptions.ExceptionService;
import com.subasta2.Proyecto.Final.Egg.repositories.ObjectsRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {

    private ObjectsRepository objectsRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public ObjectsService(ObjectsRepository objectsRepository, CustomerRepository customerRepository) {
        this.objectsRepository = objectsRepository;
        this.customerRepository = customerRepository;
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
    public Objects edit(String id, String name, String description, Double initialValue,
            State state, Category category, String customerId, String auctionId) throws ExceptionService {

        Objects object = findById(id);
        if (!name.trim().isEmpty() || name != null) {
            object.setName(name);
        }
        if (!description.trim().isEmpty() || description != null) {
            object.setDescription(description);
        }
        if (!initialValue.toString().trim().isEmpty() || initialValue != null) {
            object.setInitialValue(initialValue);
        }
        if (state != null) {
            object.setState(state);
        }
        if (category != null) {
            object.setCategory(category);
        }
        if (!customerId.trim().isEmpty() || customerId != null) {
            object.setCustomer(findById);
        }
        return object;
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
        if (object.getCategory == null) {
            throw new ExceptionService("Tiene que indicar la categoria");
        }
        if (object.getState == null) {
            throw new ExceptionService("Tiene que indicar el estado");
        }
    }
}
