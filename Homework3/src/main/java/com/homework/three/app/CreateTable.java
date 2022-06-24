package com.homework.three.app;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateTable {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+";
    
    int addCol;
    String[][] newTable;

    public void stringTable(HashMap<String, String> pair, String[][] table,
            int row, int col) {
        
        int total = row * col;
        int count = 0;
        
        String[] key = new String[total];
        String[] val = new String[total];

        for (int i = 0; i < total; i++) {
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
    }
    
    public void addColumn(String[][] table, int row, int col, int position,
            String key, String val, String[] arrKey, String[] arrVal) {

        addCol = col + 1;
        newTable = new String[row][addCol];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newTable[i][j] = table[i][j];
            }
        }
        
        arrKey[position] = key;
        arrVal[position] = val;
        
        newTable[position][col] = arrKey[position] + "," + arrVal[position];
    }
	
}