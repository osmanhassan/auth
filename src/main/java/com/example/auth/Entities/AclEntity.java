package com.example.auth.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "acl", schema = "auth", catalog = "")
public class AclEntity implements Serializable {
    private Integer id;
    private String role;
    private String url;
    private Integer isPattern;
    private RolesEntity rolesByRole;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROLE", insertable = false, updatable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "IS_PATTERN")
    public Integer getIsPattern(){
        return isPattern;
    }

    public void setIsPattern(Integer isPattern) {
        this.isPattern = isPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AclEntity aclEntity = (AclEntity) o;
        return Objects.equals(id, aclEntity.id) &&
                Objects.equals(role, aclEntity.role) &&
                Objects.equals(url, aclEntity.url) &&
                Objects.equals(isPattern, aclEntity.isPattern);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, url, isPattern);
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ROLE", referencedColumnName = "ROLE", nullable = false)
    public RolesEntity getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(RolesEntity rolesByRole) {
        this.rolesByRole = rolesByRole;
    }
}
