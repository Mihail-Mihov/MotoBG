package com.example.myproject.model.binding;

import com.example.myproject.model.entity.PictureEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    @NotBlank (message = "Заглавието е задължително")
    private String title;
    private String imageUrl;
    @Positive
    @NotNull (message = "Въведи валидна цена")
    private Double price;
    @NotBlank (message = "Добави описание")
    private String description;
    @NotNull (message = "Добави състояние")
    private String itemCondition;
    @NotNull (message = "Добави марка")
    private String brand;
    @NotNull (message = "Добави модел")
    private String model;
    @NotNull (message = "Добави град")
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past (message = "Добави година")
    private Date year;
    @NotNull
    private MultipartFile picture;
    private List<PictureEntity> pictures;
    private boolean canDelete;
    private String sellerFullName;
    private Double oldPrice;
    private Instant created;
    private Instant modified;

}