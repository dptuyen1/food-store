/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service;

import com.dpt.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dptuy
 */
public interface UserService extends UserDetailsService {

    List<User> getUsers();

    User getById(int id);

    User getByUsername(String username);

//Server
    User addOrUpdate(User user);
//    

//    Client
    User add(Map<String, String> params);

    User update(int id, User user);

    User addAvatar(MultipartFile file);
//

    int delete(int id);

    boolean auth(String username, String password);
}
