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
    
    public void updateUser() {
        
        //utx.begin();
        em.getTransaction().begin();
        //create named query to update user details
        Query query;
        query = em.createNamedQuery("User.updateById");
        query.setParameter("username", this.username);
        query.setParameter("name", this.name);
        query.setParameter("bio", this.bio);
        query.setParameter("userId", this.userID);
        
        query.executeUpdate();
        //utx.commit();
        em.getTransaction().commit();
    }
    
}
