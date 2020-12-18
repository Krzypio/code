package com.study.automatic.rod.backend.entity;

import javax.persistence.Entity;

@Entity
public class Sample extends AbstractEntity {
    String description;

    public Sample() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
