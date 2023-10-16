/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.formatters;

import com.dpt.pojo.Role;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author dptuy
 */
public class RoleFormatter implements Formatter<Role> {

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        int id = Integer.parseInt(text);
        return new Role(id);
    }

}
