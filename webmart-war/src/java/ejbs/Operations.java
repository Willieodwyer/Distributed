/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import project.entities.Products;

/**
 *
 * @author Brian
 */
@Stateless
public class Operations {

    @PersistenceContext(unitName = "webmart-warPU")
    private EntityManager em;

public List<Products> getProducts(){
    return em.createQuery("SELECT p FROM Products p").getResultList();

}

public int checkIfQueryExists(String query){
    List <Products> products = em.createQuery("SELECT p FROM Products p WHERE p.description = :description").setParameter("description", query).getResultList();
    return products.size();
}

public Products returnProducts(String query){
    Products product = (Products)em.createQuery("SELECT p FROM Products p WHERE p.description = :description").setParameter("description", query).getSingleResult();
    return product;
}
} 
