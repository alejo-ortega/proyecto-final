package com.subasta2.Proyecto.Final.Egg.interfaces;

import java.util.List;
import javax.transaction.Transactional;

/**
 * Interface that helps in the creation of important methods in the chosen services.
 * @author Nico
 * @param <Object>
 */
public interface ServiceInterface <Object>{
    
    /**
     * This method persists the changes into the Data Base.
     * Can be used within update or create methods.
     * @param object
     * @throws Exception 
     */
    @Transactional(rollbackOn = {Exception.class})
    public void save (Object T) throws Exception;
    
    /**
     * This method is used to validate data entered by the user. It should help preventing
     * exceptions.
     * @param object
     * @throws Exception 
     */
    public void validate (Object T) throws Exception;
    
    /**
     * Shows a list with all the objects of the selected type.
     * @return 
     */
    @Transactional
    public List <Object> showList();
    
    /**
     * Shows one object based on its id attribute.
     * @param id
     * @return 
     */
    @Transactional
    public Object showOne (String id);
    
    /**
     * Sets object attribute "active" to false
     */
    public void deactivate ();
    
    /**
     * Sets object attribute "active" to true
     */
    public void activate ();
}
