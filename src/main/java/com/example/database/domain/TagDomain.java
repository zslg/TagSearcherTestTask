package com.example.database.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tag")
public class TagDomain {

    @Id
    @NotNull
    private String name;

    public TagDomain(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
