package com.example.myproject.model.binding;

import com.example.myproject.model.entity.PictureEntity;
import com.example.myproject.model.enums.ConditionEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OfferDTO {

    private Long id;
    @NotNull
    private String tittle;
    private String imageUrl;
    @NotNull
    @DecimalMin("1")
    private Double price;
    @NotNull
    private String description;
    private String itemCondition;
    private String brand;
    private String model;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date year;
    private List<PictureEntity> pictures;
    private boolean canDelete;
    private String sellerFullName;

    //    private Instant created;
//    private Instant modified;

}