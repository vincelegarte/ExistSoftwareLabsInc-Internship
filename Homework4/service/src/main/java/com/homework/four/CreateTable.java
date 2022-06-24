package com.homework.four;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateTable {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+";

    public String[][] stringTable(String[][] table) {

        int row = table.length;
        int col = table[0].length;
        Map<String, String> pair = new HashMap();
        
        int totalLength = row * col;
        int count = 0;
        
        String[] key = new String[totalLength];
        String[] val = new String[totalLength];

        for (int i = 0; i < totalLength; i++) {
            pair.put(RandomStringUtils.random(3, CHARS), RandomStringUtils.random(3, CHARS));
            key = pair.keySet().toArray(new String[i]);
            val = pair.values().toArray(new String[i]);
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                table[i][j] = val[count]+","+key[count];
                count++;
            }
        }

        return table;
    }
    
    public String[][] addColumn(String[][] table, int position,
            String key, String val, String[] arrKey, String[] arrVal) {

        int row = table.length;
        int col = table[0].length;
        String holder;
        
        int addCol = col + 1;
        String[][] newTable = new String[row][addCol];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                holder = table[i][j];
                newTable[i][j] = holder;
            }
        }
        
        arrKey[position] = key;
        arrVal[position] = val;
        
        newTable[position][col] = arrKey[position] + "," + arrVal[position];

        return newTable;
    }
	
}