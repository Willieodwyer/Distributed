/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import ejbs.Operations;
import project.entities.Products;

/**
 *
 * @author Brian
 */
@Named(value = "index")
@Dependent
public class index implements Serializable{

    @EJB
    private Operations operations;

    /**
     * Creates a new instance of index
     */
    public index() {
    }
    
    public List<Products> getProducts(){
        return operations.getProducts();
    }
}
