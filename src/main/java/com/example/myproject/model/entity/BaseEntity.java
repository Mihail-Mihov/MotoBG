package com.example.myproject.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract  class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant created;
    private Instant modified;

    @PrePersist
    public void beforeCreate(){
        this.created = Instant.now();
    }

    @PostPersist
    public void onUpdate(){
        this.modified = Instant.now();
    }

}
