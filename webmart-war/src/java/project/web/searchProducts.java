/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import data.Product;
import data.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import project.entities.Products;

/**
 *
 * @author werl
 */
@Named(value = "searchProducts")
@SessionScoped
public class searchProducts  implements Serializable{

    /**
     * Creates a new instance of searchProducts
     */
    public searchProducts() {
    }
    
    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String productID = "";
    private String productDescription = "";
    private List<Products> products;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    //DATABSE STUFF
     /**
     * Searches for product by ID
     */
    public void searchID() {
        // create named query and set parameter
        this.result = "";
        if(!this.productID.equals("")){
            Query query;
        query = em.createNamedQuery("Products.findByID")
                .setParameter("prodID", Integer.parseInt(this.productID));
                // set result as query result
        if(query.getResultList().size() > 0) {
            this.products = (List<Products>) query.getResultList();
//            this.result = query.getResultList().get(0).toString();
            //this.result = products.getDescription();
        }   
        else 
            this.result = "No match found";
        }
        else
            this.result = "No ID entered";
        
    }
    
    /**
     * Searches for product by ID
     */
    public void searchDescription() {
        // create named query and set parameter
        this.result = "";
        if(!this.productDescription.equals("")){
            Query query;
            query = em.createNamedQuery("Products.findByDescription")
                .setParameter("pDesc", "%" + this.productDescription + "%");
                // set result as query result
            if(query.getResultList().size() > 0) {
            this.products = (List<Products>) query.getResultList();
//            this.result = query.getResultList().get(0).toString();
            //this.result = products.getDescription();
            }
            else 
            this.result = "No match found";
        }   
        else 
            this.result = "No description entered";
        
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
    
}
