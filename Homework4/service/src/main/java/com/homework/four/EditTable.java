package com.homework.four;

import org.apache.commons.lang3.StringUtils; 

public class EditTable {
    
    public String[][] changeValues(String[][] table, int row, int col, String changeVal, String newVal){
        
        if (StringUtils.equals(changeVal, "key")){
            try {
                if (StringUtils.contains(table[row][col],"null,")){
                    String replace = StringUtils.replace(table[row][col],"null,", newVal+",");
                    table[row][col] = replace;
                } else {
                    String replace = StringUtils.replace(table[row][col],table[row][col].substring(0, 4), newVal+",");
                    table[row][col] = replace;
                }
            } catch (NullPointerException e) {
                table[row][col] = newVal+","+null;
            }
        }
        
        if (StringUtils.equals(changeVal,"value")){
            try {
                if (StringUtils.contains(table[row][col],",null")){
                    String replace = StringUtils.replace(table[row][col],",null", ","+newVal);
                    table[row][col] = replace;
                } else {
                    String replace = StringUtils.replace(table[row][col],table[row][col].substring(3, 7), ","+newVal);
                    table[row][col] = replace;
                }
            } catch (NullPointerException e){
                table[row][col] = null+","+newVal;
            }
        }

        return table;
    }
}