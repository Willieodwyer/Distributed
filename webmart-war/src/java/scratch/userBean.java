/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch;

import data.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
@ManagedBean
@SessionScoped
public class userBean implements Serializable{

    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Inject
    private UserTransaction utx;
    
    
    
    @ManagedProperty(value="#{login}")
    private Login login;
    private String username;
    private String name;
    private String bio;
    private int userID;
    private User user;
    
   
    /**
     * Creates a new instance of userBean
     */
    public userBean() {
    }

    public String getUsername() {
        if(login != null){
            username = login.getUsername();
            user = getUser();
        }
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    
    public Login getLogin(){
        return login;
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return user.getBio();
    }
    
    public String getEscapedBio() {
        return user.getBio().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }

    public void setBio(String message) {
        this.bio = message;
    }

    public int getUserID() {
        return user.getUserId();
    }

    public void setUserID(int custID) {
        this.userID = custID;
    }

    public User getUser() {
        // create named query and set parameter
        Query query;
        query = em.createNamedQuery("User.findByUsername")
                .setParameter("username", this.username);
        // set result as query result
        user = (User) query.getSingleResult();
        
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String updateUser() {
        
       User userMod = em.find(User.class, userID);
       if(userMod != null){
            userMod.setUsername(username);
            userMod.setBio(bio);
            userMod.setName(name);
            merge(userMod);
       }
       return "valid";
       
    }

    public void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
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
