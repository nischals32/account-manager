package com.app.account.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class OperationType {
    public OperationType() {

    }
    public OperationType(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id ()
    @Column(name="OperationType_id")
    @GeneratedValue
    private long id;

    @Column(name="Description", unique = true)
    private String description;
}

