package com.example.auth.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "auth", catalog = "")
public class RolesEntity {
    private String role;
    private Collection<AclEntity> aclsByRole;
    private Collection<AuthoritiesEntity> authoritiesByRole;

    @Id
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(role);
    }

    @OneToMany(mappedBy = "rolesByRole")
    public Collection<AclEntity> getAclsByRole() {
        return aclsByRole;
    }

    public void setAclsByRole(Collection<AclEntity> aclsByRole) {
        this.aclsByRole = aclsByRole;
    }

    @OneToMany(mappedBy = "rolesByRole")
    public Collection<AuthoritiesEntity> getAuthoritiesByRole() {
        return authoritiesByRole;
    }

    public void setAuthoritiesByRole(Collection<AuthoritiesEntity> authoritiesByRole) {
        this.authoritiesByRole = authoritiesByRole;
    }
}
