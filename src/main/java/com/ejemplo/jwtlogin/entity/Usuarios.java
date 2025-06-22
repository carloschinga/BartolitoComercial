package com.ejemplo.jwtlogin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
        @NamedQuery(name = "Usuarios.findByUsecod", query = "SELECT u FROM Usuarios u WHERE u.usecod = :usecod"),
        @NamedQuery(name = "Usuarios.findBySiscod", query = "SELECT u FROM Usuarios u WHERE u.siscod = :siscod"),
        @NamedQuery(name = "Usuarios.findByUsepas", query = "SELECT u FROM Usuarios u WHERE u.usepas = :usepas"),
        @NamedQuery(name = "Usuarios.findByUsenam", query = "SELECT u FROM Usuarios u WHERE u.usenam = :usenam"),
        @NamedQuery(name = "Usuarios.findByUseusr", query = "SELECT u FROM Usuarios u WHERE u.useusr = :useusr"),
        @NamedQuery(name = "Usuarios.findByUsesgl", query = "SELECT u FROM Usuarios u WHERE u.usesgl = :usesgl"),
        @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado"),
        @NamedQuery(name = "Usuarios.findByFeccre", query = "SELECT u FROM Usuarios u WHERE u.feccre = :feccre"),
        @NamedQuery(name = "Usuarios.findByFecumv", query = "SELECT u FROM Usuarios u WHERE u.fecumv = :fecumv"),
        @NamedQuery(name = "Usuarios.findByUsecodx", query = "SELECT u FROM Usuarios u WHERE u.usecodx = :usecodx"),
        @NamedQuery(name = "Usuarios.findByUsenamx", query = "SELECT u FROM Usuarios u WHERE u.usenamx = :usenamx"),
        @NamedQuery(name = "Usuarios.findByHostname", query = "SELECT u FROM Usuarios u WHERE u.hostname = :hostname"),
        @NamedQuery(name = "Usuarios.findByTidcod", query = "SELECT u FROM Usuarios u WHERE u.tidcod = :tidcod"),
        @NamedQuery(name = "Usuarios.findByUsedoc", query = "SELECT u FROM Usuarios u WHERE u.usedoc = :usedoc"),
        @NamedQuery(name = "Usuarios.findByUsemail", query = "SELECT u FROM Usuarios u WHERE u.usemail = :usemail"),
        @NamedQuery(name = "Usuarios.findByUsestafmag", query = "SELECT u FROM Usuarios u WHERE u.usestafmag = :usestafmag") })
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usecod")
    private Integer usecod;
    @Basic(optional = false)
    @Column(name = "siscod")
    private int siscod;
    @Basic(optional = false)
    @Column(name = "usepas")
    private String usepas;
    @Basic(optional = false)
    @Column(name = "usenam")
    private String usenam;
    @Basic(optional = false)
    @Column(name = "useusr")
    private String useusr;
    @Basic(optional = false)
    @Column(name = "usesgl")
    private String usesgl;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "feccre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feccre;
    @Basic(optional = false)
    @Column(name = "fecumv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecumv;
    @Basic(optional = false)
    @Column(name = "usecodx")
    private int usecodx;
    @Basic(optional = false)
    @Column(name = "usenamx")
    private String usenamx;
    @Basic(optional = false)
    @Column(name = "hostname")
    private String hostname;
    @Basic(optional = false)
    @Column(name = "tidcod")
    private String tidcod;
    @Column(name = "usedoc")
    private String usedoc;
    @Column(name = "usemail")
    private String usemail;
    @Basic(optional = false)
    @Column(name = "usestafmag")
    private String usestafmag;
    @JoinColumn(name = "grucod", referencedColumnName = "grucod")
    @ManyToOne(optional = false)
    private Grupos grucod;

    public Usuarios() {
    }

    public Usuarios(Integer usecod) {
        this.usecod = usecod;
    }

    public Usuarios(Integer usecod, int siscod, String usepas, String usenam, String useusr, String usesgl,
            String estado, Date feccre, Date fecumv, int usecodx, String usenamx, String hostname, String tidcod,
            String usestafmag) {
        this.usecod = usecod;
        this.siscod = siscod;
        this.usepas = usepas;
        this.usenam = usenam;
        this.useusr = useusr;
        this.usesgl = usesgl;
        this.estado = estado;
        this.feccre = feccre;
        this.fecumv = fecumv;
        this.usecodx = usecodx;
        this.usenamx = usenamx;
        this.hostname = hostname;
        this.tidcod = tidcod;
        this.usestafmag = usestafmag;
    }

    public Integer getUsecod() {
        return usecod;
    }

    public void setUsecod(Integer usecod) {
        this.usecod = usecod;
    }

    public int getSiscod() {
        return siscod;
    }

    public void setSiscod(int siscod) {
        this.siscod = siscod;
    }

    public String getUsepas() {
        return usepas;
    }

    public void setUsepas(String usepas) {
        this.usepas = usepas;
    }

    public String getUsenam() {
        return usenam;
    }

    public void setUsenam(String usenam) {
        this.usenam = usenam;
    }

    public String getUseusr() {
        return useusr;
    }

    public void setUseusr(String useusr) {
        this.useusr = useusr;
    }

    public String getUsesgl() {
        return usesgl;
    }

    public void setUsesgl(String usesgl) {
        this.usesgl = usesgl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFeccre() {
        return feccre;
    }

    public void setFeccre(Date feccre) {
        this.feccre = feccre;
    }

    public Date getFecumv() {
        return fecumv;
    }

    public void setFecumv(Date fecumv) {
        this.fecumv = fecumv;
    }

    public int getUsecodx() {
        return usecodx;
    }

    public void setUsecodx(int usecodx) {
        this.usecodx = usecodx;
    }

    public String getUsenamx() {
        return usenamx;
    }

    public void setUsenamx(String usenamx) {
        this.usenamx = usenamx;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTidcod() {
        return tidcod;
    }

    public void setTidcod(String tidcod) {
        this.tidcod = tidcod;
    }

    public String getUsedoc() {
        return usedoc;
    }

    public void setUsedoc(String usedoc) {
        this.usedoc = usedoc;
    }

    public String getUsemail() {
        return usemail;
    }

    public void setUsemail(String usemail) {
        this.usemail = usemail;
    }

    public String getUsestafmag() {
        return usestafmag;
    }

    public void setUsestafmag(String usestafmag) {
        this.usestafmag = usestafmag;
    }

    public Grupos getGrucod() {
        return grucod;
    }

    public void setGrucod(Grupos grucod) {
        this.grucod = grucod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usecod != null ? usecod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usecod == null && other.usecod != null)
                || (this.usecod != null && !this.usecod.equals(other.usecod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemplo.jwtlogin.entity.Usuarios[ usecod=" + usecod + " ]";
    }

}
