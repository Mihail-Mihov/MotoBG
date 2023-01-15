package com.example.myproject.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity{

    @Column(nullable = false)
    String brand;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    Set<ModelEntity> models = new HashSet<>();
}
