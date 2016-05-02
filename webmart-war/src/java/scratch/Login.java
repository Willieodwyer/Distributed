/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.User;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author werl
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable{
    
    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of SessionPage
     */
    private String username = "";
    private String password = "";
    private String result = "";
    private boolean isAdmin = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    //DATABSE STUFF
        /**
     * Returns true/false if a user with the given parameters exists
     * 
     * @param name
     * @param password
     * @return true/false if list contains/doesn't contain a user
     */
    public void checkUserLogin() {
        // create named query and set parameter
        Query query;
        query = em.createNamedQuery("User.checkUserLogin")
                .setParameter("username", this.username)
                .setParameter("password",this.password);
        // set result as query result
        if(query.getResultList().size() > 0) {
            this.result =  query.getResultList().get(0).toString();
            if(((User)query.getSingleResult()).getAdmin() == 1)
                isAdmin = true;
        } else
            this.result = "Incorrect login details";
        
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    /* Check if user has access to this page */
    public void checkAccess() throws IOException {
        if(!isAdmin)
            FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml");
    }

    /**
     * Creates a new instance of FormData
     */
    public Login() {
    }
    
}
