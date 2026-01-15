package com.javaweb.services.Impl;

import com.javaweb.components.JwtTokenUtil;
import com.javaweb.dto.UserDTO;
import com.javaweb.entity.Role;
import com.javaweb.entity.User;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServicesImpl implements IUserServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String username = userDTO.getUsername();
        //check username
        if(userRepository.existsByUsername(username)){
            throw new DataIntegrityViolationException("Username already exists");
        }
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new Exception("Role id not found"));

        //check authortication
//        if(role.getName().toUpperCase().equals("ADMIN")){
//            throw new PermissionDenyException("You cannot register an admin account");
//        }
        //convert from userDTO => userEntity
        User newUser = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        newUser.getRoles().add(role);
        return userRepository.save(newUser);
    }

    @Override
    public String login(String username, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new Exception("Username not found");
        User existingUser = optionalUser.get();
        //check password
        if(!passwordEncoder.matches(password, existingUser.getPassword())){
            throw new Exception("Wrong password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, existingUser.getAuthorities());

        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}
