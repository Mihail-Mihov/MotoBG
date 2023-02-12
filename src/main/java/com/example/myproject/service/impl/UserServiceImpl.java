package com.example.myproject.service.impl;

import com.example.myproject.model.binding.UserDTO;
import com.example.myproject.model.entity.UserEntity;
import com.example.myproject.model.entity.UserRoleEntity;
import com.example.myproject.model.entity.UserRoleEnum;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.repository.UserRoleRepository;
import com.example.myproject.service.UserService;
import com.example.myproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDetailsServiceImpl myUserDetailsService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           UserRoleRepository userRoleRepository, UserDetailsServiceImpl myUserDetailsService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.myUserDetailsService = myUserDetailsService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeUsersAndRoles() {
      initializeRoles();
        //initializeUsers();

    }

    @Override
    public Set<UserDTO> getAllUsers() {

        Random random = new Random();
        Set<UserDTO> list = new HashSet<>();

        if (userRepository.count() >= 3) {
            for (int i=0; i<3; i++){
                Long id = random.nextLong(1,this.userRepository.count());
                UserEntity byId =  findById(id);
                UserDTO profileHomeView = mapToHomeView(byId);
                list.add(profileHomeView);
            }
        }
        return list;
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    private UserDTO mapToHomeView(UserEntity user){
        UserDTO profileHomeView = modelMapper.map(user, UserDTO.class);

        profileHomeView.setUsername(user.getUsername());
        profileHomeView.setId(user.getId());
        profileHomeView.setProfilePictureUrl(user.getProfilePictureUrl());

        if (user.getDescription() == null){
            profileHomeView.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer");
        } else {
            if (user.getDescription().length() > 200) {
                profileHomeView.setDescription( user.getDescription().substring(0, 200));
            }
            else {
                profileHomeView.setDescription(user.getDescription());
            }
        }

        return profileHomeView;
    }


    public UserEntity getUserByUsername(String username){
       return userRepository.findByUsername(username).orElseThrow();
    }

    private void initializeRoles() {

        if (userRoleRepository.count() ==0){
            UserRoleEntity adminRole =new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            UserRoleEntity moderatorRole = new UserRoleEntity();
            moderatorRole.setRole(UserRoleEnum.MODERATOR);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
            userRoleRepository.save(moderatorRole);
        }
    }

    @Override
    public void updateProfile(UserDTO profile) {
        UserEntity userEntity =  userRepository.findById(profile.getId()).orElseThrow(() ->
                new ObjectNotFoundException("There is no profile with id: " + profile.getId()));
        userEntity.setId(profile.getId());
        userEntity.setEmail(profile.getEmail());
        userEntity.setFirstName(profile.getFirstName());
        userEntity.setLastName(profile.getLastName());
        userEntity.setProfilePictureUrl(profile.getProfilePictureUrl());
        userEntity.setPhoneNumber(profile.getPhoneNumber());
        userEntity.setHomeTown(profile.getHomeTown());
        userEntity.setDescription(profile.getDescription());
        userRepository.save(userEntity);
    }



    @Override
    public void registerAndLoginUser(UserDTO userDTO) {

         UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity user = new UserEntity();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setActive(1);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setHomeTown(userDTO.getHomeTown());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (user.getUsername().equals("admin")) {
            user.setRoles(Set.of(userRoleRepository.findByRole(UserRoleEnum.ADMIN)));
        } else {
        user.setRoles(Set.of(userRole));
        }

        userRepository.save(user);

        // this is the Spring representation of a user

        UserDetails principal =  myUserDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            principal,
                user.getPassword()
                ,principal.getAuthorities()
        );

        SecurityContextHolder
                .getContext().setAuthentication(authentication);

    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
