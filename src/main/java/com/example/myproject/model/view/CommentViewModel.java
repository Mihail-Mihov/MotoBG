package com.example.myproject.model.view;

import com.example.myproject.model.entity.OfferEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String comment;
    private OfferEntity offer;
//    private String created;
    private boolean canApprove;
    private boolean canDelete;
}
