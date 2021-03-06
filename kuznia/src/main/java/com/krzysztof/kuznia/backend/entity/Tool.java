package com.krzysztof.kuznia.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Tool extends AbstractEntity {

    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters and must be unique")
    @Column(unique=true)    //unique field in database, its not validation
    String name;

    //constructors

    public Tool(String name) {
        this();
        setName(name);
    }

    public Tool() {
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
