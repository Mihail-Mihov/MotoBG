package com.example.myproject.model.entity;

import com.example.myproject.model.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class OfferEntity extends BaseEntity{

    @Column(nullable = false)
    private String tittle;
    private Integer rate;
    // TODO @Column(columnDefinition = "longtext")
    @Lob
    @Enumerated(EnumType.STRING)
    private String imageUrl;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    @Lob
    private String description;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserEntity author;
    //@Enumerated(EnumType.STRING)
    @Column(name = "item_condition")
    private String itemCondition;
    private String brand;
    private String model;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date year;
    @OneToMany(mappedBy = "offer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
    private List<PictureEntity> pictures;

    public OfferEntity() {
        this.rate = 0;
    }
}
