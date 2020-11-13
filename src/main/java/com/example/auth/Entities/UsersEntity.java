package com.example.auth.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "auth", catalog = "")
public class UsersEntity {
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Collection<AuthoritiesEntity> authoritiesByUserName;

    public UsersEntity(){}
    public UsersEntity(UsersEntity usersEntity){

        this.userName = usersEntity.getUserName();
        this.password = usersEntity.getPassword();
        this.email = usersEntity.getEmail();
        this.phone = usersEntity.getPhone();
        this.authoritiesByUserName = usersEntity.getAuthoritiesByUserName();

    }

    @Id
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, password, email, phone);
    }

    @JsonBackReference
    @OneToMany(mappedBy = "usersByUserName", fetch = FetchType.EAGER)
    public Collection<AuthoritiesEntity> getAuthoritiesByUserName() {
        return authoritiesByUserName;
    }

    public void setAuthoritiesByUserName(Collection<AuthoritiesEntity> authoritiesByUserName) {
        this.authoritiesByUserName = authoritiesByUserName;
    }
}
