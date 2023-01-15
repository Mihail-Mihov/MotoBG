package com.example.myproject.model.binding;

import com.example.myproject.model.validator.UniqueUserName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    private Long id;
//    @NotBlank
//    @Size(min=4, max=20)
//    @UniqueUserName()
    private String username;
//    @NotNull
//    @Email(message = "Email should be valid")
    private String email;
//    @NotNull
//    @Size(min=4, max=20)
    private String firstName;
//    @NotNull
//    @Size(min=4, max=20)
    private String lastName;
    private String phoneNumber;
    private String homeTown;
//    @NotNull
//    @Size(min=4, max=20)
    private String password;
//    @NotNull
//    @Size(min=4, max=20)
    private String confirmPassword;
    private String profilePictureUrl;
    private boolean isActive;
    private String description;
    private boolean canUpdate = false;

}
