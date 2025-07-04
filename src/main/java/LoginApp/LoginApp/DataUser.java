/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginApp.LoginApp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Acer
 */
@Entity
@Table(name = "data_user")
@NamedQueries({
    @NamedQuery(name = "DataUser.findAll", query = "SELECT d FROM DataUser d"),
    @NamedQuery(name = "DataUser.findByIdUser", query = "SELECT d FROM DataUser d WHERE d.idUser = :idUser"),
    @NamedQuery(name = "DataUser.findByUsername", query = "SELECT d FROM DataUser d WHERE d.username = :username"),
    @NamedQuery(name = "DataUser.findByEmail", query = "SELECT d FROM DataUser d WHERE d.email = :email"),
    @NamedQuery(name = "DataUser.findByPassword", query = "SELECT d FROM DataUser d WHERE d.password = :password")})
public class DataUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public DataUser() {
    }

    public DataUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataUser)) {
            return false;
        }
        DataUser other = (DataUser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginApp.LoginApp.DataUser[ idUser=" + idUser + " ]";
    }
    
}
