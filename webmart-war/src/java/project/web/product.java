/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ejbs.Operations;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import project.entities.Products;


/**
 *
 * @author Brian
 */
@Named(value = "product")
@ManagedBean
@RequestScoped
public class product implements Serializable {

    @EJB
    private Operations operations;

    
    @ManagedProperty(value = "#{shoppingCart}")
    shoppingCart cart;

    public shoppingCart getCart() {
        return cart;
    }

    public void setCart(shoppingCart cart) {
        this.cart = cart;
    }

    
    /**
     * Creates a new instance of product
     */
    public product() {
    }
    
    
    public String getQuery(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
         
    }
    
    public void checkIfQueryExists()throws IOException {
        if(operations.checkIfQueryExists(getQuery()) == 0){
            FacesContext.getCurrentInstance().getExternalContext().redirect("errorPage.xhtml");
        }
    }
    
    public Products getProduct(){
        return operations.returnProducts(getQuery());
    }
    
    public void addToCart(){
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
        cart.addtoCart(operations.returnProducts(query));
        
    }
}
