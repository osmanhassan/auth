package com.example.auth.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities", schema = "auth", catalog = "")
public class AuthoritiesEntity {
    private Integer id;
    private String userName;
    private String role;
    private UsersEntity usersByUserName;
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
    @Column(name = "USER_NAME", insertable = false, updatable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "ROLE", insertable = false, updatable = false)
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
        AuthoritiesEntity that = (AuthoritiesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, role);
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME", nullable = false)
    public UsersEntity getUsersByUserName() {
        return usersByUserName;
    }

    public void setUsersByUserName(UsersEntity usersByUserName) {
        this.usersByUserName = usersByUserName;
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
