/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bianc
 */
@Entity
@Table(name = "prato_principal")
@NamedQueries({
    @NamedQuery(name = "PratoPrincipal.findAll", query = "SELECT p FROM PratoPrincipal p")})
public class PratoPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prato_principal")
    private Integer idPratoPrincipal;
    @Basic(optional = false)
    @Column(name = "nome_prato_principal")
    private String nomePratoPrincipal;
    @Column(name = "status")
    private Short status;

    public PratoPrincipal() {
    }

    public PratoPrincipal(Integer idPratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
    }

    public PratoPrincipal(Integer idPratoPrincipal, String nomePratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
        this.nomePratoPrincipal = nomePratoPrincipal;
    }

    public Integer getIdPratoPrincipal() {
        return idPratoPrincipal;
    }

    public void setIdPratoPrincipal(Integer idPratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
    }

    public String getNomePratoPrincipal() {
        return nomePratoPrincipal;
    }

    public void setNomePratoPrincipal(String nomePratoPrincipal) {
        this.nomePratoPrincipal = nomePratoPrincipal;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPratoPrincipal != null ? idPratoPrincipal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PratoPrincipal)) {
            return false;
        }
        PratoPrincipal other = (PratoPrincipal) object;
        if ((this.idPratoPrincipal == null && other.idPratoPrincipal != null) || (this.idPratoPrincipal != null && !this.idPratoPrincipal.equals(other.idPratoPrincipal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PratoPrincipal[ idPratoPrincipal=" + idPratoPrincipal + " ]";
    }
    
}
