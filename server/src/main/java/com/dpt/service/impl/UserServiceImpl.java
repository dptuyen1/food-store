/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dpt.pojo.Role;
import com.dpt.pojo.User;
import com.dpt.repository.RoleRepository;
import com.dpt.repository.UserRepository;
import com.dpt.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dptuy
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User không tồn tại");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleId().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @Override
    public User getById(int id) {
        return this.userRepository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.getByUsername(username);
//
//        try {
//            return this.userRepository.getByUsername(username);
//        } catch (NoResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
    }

    @Override
    public User addOrUpdate(User user) {
        if (user.getId() == null) {
            List<Role> roles = this.roleRepository.getRoles();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedDate(new Date());
            user.setRoleId(roles.get(1));
        }
        return this.userRepository.addOrUpdate(user);
    }

    @Override
    public User add(Map<String, String> params) {
        List<Role> roles = this.roleRepository.getRoles();

        User user = new User();
        user.setLastName(params.get("lastName"));
        user.setFirstName(params.get("firstName"));
        user.setEmail(params.get("email"));
        user.setUsername(params.get("username"));
        user.setPassword(this.passwordEncoder.encode(params.get("password")));
        user.setCreatedDate(new Date());
        user.setRoleId(roles.get(2));

        return this.userRepository.addOrUpdate(user);
    }

    @Override
    public User update(int id, User user) {
        User u = this.userRepository.getById(id);

        u.setLastName(user.getLastName());
        u.setFirstName(user.getFirstName());
        u.setEmail(user.getEmail());
        u.setAddress(user.getAddress());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setAvatar(user.getAvatar());

        return this.userRepository.addOrUpdate(u);
    }

    @Override
    public User addAvatar(MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepository.getByUsername(authentication.getName());

        if (file != null && !file.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.userRepository.addOrUpdate(u);
    }

    @Override
    public int delete(int id) {
        User user = this.userRepository.getById(id);

        return this.userRepository.delete(user);
    }

    @Override
    public boolean auth(String username, String password) {
        User user = this.userRepository.getByUsername(username);

        if (user != null) {
            return this.passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
