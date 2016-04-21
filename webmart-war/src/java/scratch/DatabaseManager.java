/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch;

import data.*;
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
@Named(value = "db")
@RequestScoped
public class DatabaseManager {
    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String name;
    
    /**
     * Creates a new instance of ShowCustomerBean
     */
    public DatabaseManager() {
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
    
    /* Initialise database tables if empty */
    public void setup() {
        Query query = em.createNamedQuery("User.findAll");
        if(query.getResultList().size() == 0) {
            System.out.print("Initialising database..");
            
            /* Users */
            User usera = new User(1, "toor", "4uIdo0!", 1, "Toor", "Admin");
            persist(usera);
            User userb = new User(2, "joe", "1D10T?", 0, "Joe", "First customer");
            persist(userb);
            
            /* Products */
            Product proda = new Product(1, "i5-2500K", 133.00, 2);
            persist(proda);
            Product prodb = new Product(2, "i5-2550K", 147.00, 5);
            persist(prodb);
            Product prodc = new Product(3, "i5-3470S", 105.00, 4);
            persist(prodc);
            Product prodd = new Product(4, "i5-6400", 227.00, 7);
            persist(prodd);
            Product prode = new Product(5, "i7-6700K", 299.00, 8);
            persist(prode);
            
            /* Orders */
            OrderClass ordera = new OrderClass(1, userb);
            persist(ordera);
            
            /* Order Items */
            OrderItem itema = new OrderItem(1, ordera, proda, 1);
            persist(itema);
            OrderItem itemb = new OrderItem(2, ordera, prode, 2);
            persist(itemb);
        }
    }
    
}
