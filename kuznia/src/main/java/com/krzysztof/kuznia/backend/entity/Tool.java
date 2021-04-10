package com.krzysztof.kuznia.backend.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Tool extends AbstractEntity {


    @NotNull
            @NotEmpty
    String name;

    //constructors
    public Tool(@NotNull String name) {
        this.name = name;
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
