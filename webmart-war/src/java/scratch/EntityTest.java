/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch;

import data.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eoin
 */
@Named(value = "entityTest")
@RequestScoped
public class EntityTest {
    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String name = "Joe";
    
    /**
     * Creates a new instance of ShowCustomerBean
     */
    public EntityTest() {
    }

    /**
     * Returns list of customer with a given name
     * 
     * @return All customer with name customerName
     */
    public List<User> getUserByName() {
        // create named query and set parameter
        Query query = em.createNamedQuery("User.findByName")
                .setParameter("name", name);
        // return query result
        System.out.println(query.getResultList().toString());
        return query.getResultList();
    }
    
    /**
     * Getter for customerName
     * @return value of name 
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for customerName
     * @param name new value for customerName
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Auto-generated method for managing access to persist method
     * of entity manager
     * 
     * @param object Object to be made persistent
     */
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    
    
}
