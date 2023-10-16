/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.User;
import java.util.List;

/**
 *
 * @author dptuy
 */
public interface UserRepository {

    List<User> getUsers();

    User getById(int id);

    User getByUsername(String username);

    User addOrUpdate(User user);

    int delete(User user);
}
