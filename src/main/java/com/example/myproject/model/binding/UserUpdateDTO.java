package com.example.myproject.model.binding;

import com.example.myproject.model.validator.UniqueUserName;
import com.example.myproject.model.validator.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdateDTO {

    private Long id;
    private String username;
    @NotNull
    @Email(message = "Имейлът трябва да бъде валиден.")
    private String email;
    @NotNull
    @Size(min = 3, max = 15,
            message = "Името трябва да съдържа между 3 и 10 символа.")
    private String firstName;
    @NotNull
    @Size(min = 4, max = 20,
            message = "Фамилията трябва да съдържа между 4 и 10 символа.")
    private String lastName;
    private String phoneNumber;
    private String homeTown;
//    @ValidPassword(message = "Паролата трябва да съдържа между 4 и 16 символа, главна, малка буква и цифра.")
//    private String password;
//    @ValidPassword(message = "Паролите не съвпадат")
//    private String confirmPassword;
    private String profilePictureUrl;
    private boolean isActive;
    private String description;
    private boolean canUpdate = false;


}