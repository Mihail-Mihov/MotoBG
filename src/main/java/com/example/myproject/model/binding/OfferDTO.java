package com.example.myproject.model.binding;

import com.example.myproject.model.entity.PictureEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.Instant;
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
    @NotBlank
    private String tittle;
    @NotBlank
    private String imageUrl;
    @Positive
    private Double price;
    @NotBlank
    private String description;
    @NotNull
    private String itemCondition;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date year;
    private List<PictureEntity> pictures;
    private boolean canDelete;
    private String sellerFullName;
    private Double oldPrice;
    private Instant created;
    private Instant modified;

}