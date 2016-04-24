/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eoin
 */
@Entity
@Table(name = "G19USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.checkUserLogin", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAdmin", query = "SELECT u FROM User u WHERE u.admin = :admin"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByBio", query = "SELECT u FROM User u WHERE u.bio = :bio")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 30)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADMIN")
    private Integer admin;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 500)
    @Column(name = "BIO")
    private String bio;
    @OneToMany(mappedBy = "userId")
    private Collection<OrderClass> orderClassCollection;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String username, String password, Integer admin, String name, String bio) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.name = name;
        this.bio = bio;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @XmlTransient
    public Collection<OrderClass> getOrderClassCollection() {
        return orderClassCollection;
    }

    public void setOrderClassCollection(Collection<OrderClass> orderClassCollection) {
        this.orderClassCollection = orderClassCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.User[ userId=" + userId + " ]";
    }
    
}
