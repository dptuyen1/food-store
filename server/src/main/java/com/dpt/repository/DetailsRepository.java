/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.Details;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface DetailsRepository {

    List<Details> getDetails(Map<String, String> params);

    Details addOrUpdate(Details details);
}
