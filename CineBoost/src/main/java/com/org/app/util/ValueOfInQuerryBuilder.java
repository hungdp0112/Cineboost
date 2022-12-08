/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author intfs
 */
public class ValueOfInQuerryBuilder {
    
    
    public static String getValue(Object[] arr) {
        String[] str =  Arrays.copyOf(arr, arr.length,String[].class);
        StringBuilder values = new StringBuilder();
        for(String s : str) {
            String i = "'" + s + "'";
            values.append(i).append(",");
        }
        
        values = values.deleteCharAt(values.lastIndexOf(","));
        System.out.println(values.toString());
        return values.toString();
    }
    
    public static String any(String sql, int num) {
    // Create a comma-delimited list based on the number of parameters.
    final StringBuilder sb = new StringBuilder(
        String.join(", ", Collections.nCopies(num, "?")));
        System.out.println(sb.toString());

    if (sb.length() > 1) {
        sql = sql.replace("(?)", "(" + sb + ")");
    }

    return sql;
}
}
