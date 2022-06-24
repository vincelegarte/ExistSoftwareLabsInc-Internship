package com.homework.four;

import org.apache.commons.lang3.StringUtils; 

public class SearchTable {

    public String searchOccurenceandIndex(String[][] table, String search) {

        int row = table.length;
        int col = table[0].length;

        String[][] tempKey = new String[row][col];
        String[][] tempVal = new String[row][col];

        StringBuilder sb = new StringBuilder();

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
                if (StringUtils.isNoneEmpty(tempKey[i][j]) && StringUtils.contains(tempKey[i][j],search)){
					sb.append("Output: [" + i + "," + j + "] - " + StringUtils.countMatches(tempKey[i][j],search) + " Occurence found on key field\n");
				}
				if (StringUtils.isNoneEmpty(tempVal[i][j]) && StringUtils.contains(tempVal[i][j],search)){
					sb.append("Output: [" + i + "," + j + "] - " + StringUtils.countMatches(tempVal[i][j],search) + " Occurence found on value field\n");
				}
			}
		}
        return sb.toString();
    }
}