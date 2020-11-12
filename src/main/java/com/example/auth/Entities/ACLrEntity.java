package com.example.auth.Entities;

import java.io.Serializable;

public class ACLrEntity implements Serializable {

    String role;
    String url;
    Integer id;

    public ACLrEntity(String role, String url, Integer id){
        this.role = role;
        this.url = url;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
