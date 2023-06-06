package com.example.myproject.model.entity;

import com.example.myproject.model.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date year;
    @OneToMany(mappedBy = "offer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
    private List<PictureEntity> pictures;
    private Double oldPrice;
    private Instant created;
    private Instant modified;

    public OfferEntity() {
        this.rate = 0;
        oldPrice = null;
    }
    @PrePersist
    public void beforeCreate(){
        this.created = Instant.now();
    }

    @PostPersist
    public void onUpdate(){
        this.modified = Instant.now();
    }
}
