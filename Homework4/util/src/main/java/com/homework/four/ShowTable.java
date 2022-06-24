package com.homework.four;

public class ShowTable {
    
    public String printTable(String[][] table) {

        int row = table.length;
        int col = table[0].length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append("["+table[i][j]+"] ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}