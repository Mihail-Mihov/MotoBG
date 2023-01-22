package com.example.myproject.model.view;

import com.example.myproject.model.entity.BaseEntity;
import com.example.myproject.model.entity.OfferEntity;
import com.example.myproject.model.entity.UserEntity;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentViewModel extends BaseEntity {

//    private Long id;
    private String author;
    private String comment;
    private OfferEntity offer;
//    private String created;
    private boolean canApprove;
    private boolean canDelete;
}
