/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import data.Product;
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
@Named(value = "admin")
@RequestScoped
public class Admin {
    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String productAddDesc;
    private double productAddPrice;
    private int productAddStock;
    
    private int productDel; /* ID for product to delete */

    private int productModID; /* ID for product to modify */
    private int productModStock; /* New stock quantity */
    
    public int getProductDel() {
        return productDel;
    }

    public void setProductDel(int productDel) {
        this.productDel = productDel;
    }

    public int getProductModID() {
        return productModID;
    }

    public void setProductModID(int productModID) {
        this.productModID = productModID;
    }

    public int getProductModStock() {
        return productModStock;
    }

    public void setProductModStock(int productModStock) {
        this.productModStock = productModStock;
    }
    
    public Admin() {
        
    }
    
    public String getProductAddDesc() {
        return this.productAddDesc;
    }
    
    public void setProductAddDesc(String productAddDesc) {
        this.productAddDesc = productAddDesc;
    }
    
    public double getProductAddPrice() {
        return this.productAddPrice;
    }
    
    public void setProductAddPrice(double productAddPrice) {
        this.productAddPrice = productAddPrice;
    }
    
    public int getProductAddStock() {
        return this.productAddStock;
    }
    
    public void setProductAddStock(int productAddStock) {
        this.productAddStock = productAddStock;
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
    
    public void remove(Object object) {
        try {
            utx.begin();
            object = em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    /* Add new product to database */
    public void addProduct() {
        Integer newID = ((Integer)em.createQuery("SELECT max(p.productId) FROM Product p").getSingleResult()) + 1;
        Product prod = new Product(newID, productAddDesc, productAddPrice, productAddStock);;
        persist(prod);
        
        /* Send message to logs */
        String logMsg = "Product added, (" + newID + ", " + productAddDesc + ", " + productAddPrice + ", " + productAddStock + ")";
    }
    
    /* Delete product from database */
    public void deleteProduct() {
        Product prod = em.find(Product.class, productDel);
        remove(prod);
        
        /* Send message to logs */
        String logMsg = "";
    }
    
    }
    
}
