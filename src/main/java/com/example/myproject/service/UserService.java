package com.example.myproject.service;

import com.example.myproject.model.binding.UserDTO;
import com.example.myproject.model.entity.UserEntity;

import java.util.Set;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserDTO UserDTO);

    boolean isUserNameFree(String username);

     UserEntity getUserByUsername(String username);

     void updateProfile(UserDTO profile);

     Set<UserDTO> getAllUsers();

     UserEntity findById(Long id);

}
