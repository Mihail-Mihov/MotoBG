package com.example.myproject.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity author;
    private String comment;
    @ManyToOne
    private OfferEntity offer;
//    private String created;
    private boolean canApprove;
    private boolean canDelete;

}
