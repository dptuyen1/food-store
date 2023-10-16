/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface StatsRepository {

    List<Integer> getYears();

    List<Object> invoiceStats(Map<String, String> params);

    List<Object> productStats(Map<String, String> params);
}
