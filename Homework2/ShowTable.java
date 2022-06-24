package com.homework.two;

public class ShowTable {
    
    public void printTable(String[][] table, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("["+table[i][j]+"] ");
            }
            System.out.println();
        }
    }

}