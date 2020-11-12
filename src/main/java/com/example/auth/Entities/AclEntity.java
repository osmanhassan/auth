package com.example.auth.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "acl", schema = "auth", catalog = "")
public class AclEntity implements Serializable {
    private Integer id;
    private String role;
    private String url;
    private RolesEntity rolesByRole;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AclEntity aclEntity = (AclEntity) o;
        return Objects.equals(id, aclEntity.id) &&
                Objects.equals(role, aclEntity.role) &&
                Objects.equals(url, aclEntity.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, url);
    }

    @ManyToOne
    @JoinColumn(name = "ROLE", referencedColumnName = "ROLE", nullable = false)
    public RolesEntity getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(RolesEntity rolesByRole) {
        this.rolesByRole = rolesByRole;
    }
}
