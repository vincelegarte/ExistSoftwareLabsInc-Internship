package com.homework.two;

public class SearchTable {

        public void OccurenceandIndex(String table[][], int row, int col, String search) {
        String[][] tempKey = new String[row][col];
        String[][] tempVal = new String[row][col];
        
        int keyIndex = 0;
        int valIndex = 0;

        for (int i = 0; i < row; i++) { //key
            for (int j = 0; j < col; j++) {
                try { 
                    if (table[i][j].contains("null,")) {
                        tempKey[i][j] = null;
                    } else {
                        tempKey[i][j] = table[i][j].substring(0, 3);
                    }
                }catch (NullPointerException e){
                    tempKey[i][j] = null;
                }
            }
        }

        for (int i = 0; i < row; i++) { //values
            for (int j = 0; j < col; j++) {
                try {
                    if (table[i][j].contains(",null")) {
                        tempVal[i][j] = null;
                    } else if (table[i][j].contains("null,")) {
                        tempVal[i][j] = table[i][j].substring(5, 8);
                    } else {
                        tempVal[i][j] = table[i][j].substring(4, 7);
                    }
                }catch (NullPointerException e){
                    tempKey[i][j] = null;
                }
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tempKey[i][j] != null) {
                    while (true) {
                        keyIndex = tempKey[i][j].indexOf(search, keyIndex);
                        if (keyIndex != -1) {
                            keyIndex += (row*col);
                            System.out.println("Output: [" + i + "," + j + "] - " + findOccurence(tempKey[i][j], search) + " Occurence found on key field");
                        } else {
                            break;
                        }
                    }
                }
                if (tempVal[i][j] != null) {
                    while (true) {
                        valIndex = tempVal[i][j].indexOf(search, valIndex);
                        if (valIndex != -1) {
                            valIndex += (row*col);
                            System.out.println("Output: [" + i + "," + j + "] - " + findOccurence(tempVal[i][j], search) + " Occurence found on value field");
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public int findOccurence(String str, String search) {
        int index = 0;
        int occurence = 0;

        while (true) {
            index = str.indexOf(search, index);
            if (index != -1) {
                occurence++;
                index += search.length();
            } else {
                break;
            }
        }
        return occurence;
    }

}