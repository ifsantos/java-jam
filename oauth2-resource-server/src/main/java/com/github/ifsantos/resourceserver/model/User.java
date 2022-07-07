package com.github.ifsantos.resourceserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
