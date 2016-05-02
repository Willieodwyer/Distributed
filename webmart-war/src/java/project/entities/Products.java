/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Brian
 */
@Entity
@Table(name = "G19PRODUCT")
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByID", query = "SELECT p FROM Products p WHERE p.product_id = :prodID"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description LIKE :pDesc")
})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private Integer product_id;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private Integer price;
    @Size(max = 255)
    @Column(name = "STOCK")
    private String stock;

    public Products() {
    }

    public Products(Integer id) {
        this.product_id = product_id;
    }

    public Integer getId() {
        return product_id;
    }

    public void setId(Integer id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String mark) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (product_id != null ? product_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.product_id == null && other.product_id != null) || (this.product_id != null && !this.product_id.equals(other.product_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.entities.Products[ id=" + product_id + " ]";
    }
    
}
