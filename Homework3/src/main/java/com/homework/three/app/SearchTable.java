package com.homework.three.app;

import org.apache.commons.lang3.StringUtils; 

public class SearchTable {

        public void OccurenceandIndex(String table[][], int row, int col, String search) {
        String[][] tempKey = new String[row][col];
        String[][] tempVal = new String[row][col];
        
        int keyIndex = 0;
        int valIndex = 0;

        for (int i = 0; i < row; i++) { //key
            for (int j = 0; j < col; j++) {
                try { 
                    if (StringUtils.contains(table[i][j],"null,")) {
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
                    if (StringUtils.contains(table[i][j],",null")) {
                        tempVal[i][j] = null;
                    } else if (StringUtils.contains(table[i][j],"null,")) {
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
                if (StringUtils.isNoneEmpty(tempKey[i][j])) {
					if(StringUtils.contains(tempKey[i][j],search) != false){
						System.out.println("Output: [" + i + "," + j + "] - " + StringUtils.countMatches(tempKey[i][j],search) + " Occurence found on key field");
					}
				}
				if (StringUtils.isNoneEmpty(tempVal[i][j])) {
					if(StringUtils.contains(tempVal[i][j],search) != false){
						System.out.println("Output: [" + i + "," + j + "] - " + StringUtils.countMatches(tempVal[i][j],search) + " Occurence found on value field");
					}
				}
			}
		}
    }
}