package com.subasta2.Proyecto.Final.Egg.interfaces;

import java.util.List;
import javax.transaction.Transactional;

/**
 * Interface that helps in the creation of important methods in the chosen services.
 * @author Nico
 * @param <T>
 */
public interface ServiceInterface <T>{
    
    @Transactional(rollbackOn = {Exception.class})
    public void save (T object) throws Exception;
    
    public void validate (T object) throws Exception;
    
    @Transactional
    public List <T> showList();
    
    @Transactional
    public T showOne ();
}
