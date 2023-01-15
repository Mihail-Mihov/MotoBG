package com.example.myproject.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Size(min = 5)
    private String password;
    private String phoneNumber;
    private String homeTown;
    @Lob
    private String description;
    @Lob
    private String profilePictureUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRoleEntity> roles;
    private int active;

    public UserEntity() {
        this.roles = new HashSet<>();
        this.profilePictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHUvOd8Q-VihyupbJCdgjIR2FxnjGtAgMu3g&usqp=CAU";
    }
}
