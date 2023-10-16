/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.components.JwtService;
import com.dpt.dto.UserDTO;
import com.dpt.pojo.User;
import com.dpt.service.UserService;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @GetMapping(path = "/current-user")
    public ResponseEntity<UserDTO> details() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getByUsername(authentication.getName());
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/existed-user")
    public ResponseEntity<String> existed(@RequestParam(value = "username") String username) {
        User user = this.userService.getByUsername(username);

        if (user != null) {
            return new ResponseEntity<>("Existed user!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("New user!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.auth(user.getUsername(), user.getPassword())) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid user!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestParam Map<String, String> params) {
        User user = this.userService.add(params);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = "/users", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(@RequestPart(value = "avatar") MultipartFile file) {
        User u = this.userService.addAvatar(file);
        UserDTO userDTO = modelMapper.map(u, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestBody User user) {
        User u = this.userService.update(id, user);
        UserDTO userDTO = modelMapper.map(u, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable(value = "id") int id) {
        return this.userService.delete(id);
    }
}
