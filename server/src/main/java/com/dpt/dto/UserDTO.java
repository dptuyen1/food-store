/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author dptuy
 */
@Data
public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String username;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private Date createdDate;
}
