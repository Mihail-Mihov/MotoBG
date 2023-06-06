package com.example.myproject.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="pictures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String publicId;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private OfferEntity offer;
}
