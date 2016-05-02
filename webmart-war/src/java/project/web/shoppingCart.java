/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.entities.Products;

/**
 *
 * @author Brian
 */
@Named(value = "shoppingCart")
@SessionScoped
public class shoppingCart implements Serializable {

    private List<Products> products = new ArrayList<>();
    
    /**
     * Creates a new instance of shoppingCart
     */
    public shoppingCart() {
    }
    
    public void addtoCart(Products p){
        products.add(p);
    }
    
    public void removeFromCart(Products p){
        products.remove(p);
    }
    
    public int getCount(){
        return products.size();
    }
    
    public Map<Products, Integer> getCartItems()
    {
      Map<Products, Integer> cartItems = new HashMap<>();
      for(Products obj : products){
        if (cartItems.containsKey(obj))
        {
            cartItems.put(obj,cartItems.get(obj)+ 1);
        }
        else
        {
            cartItems.put(obj,1);
        }
    }
      return cartItems; 
    }
}
