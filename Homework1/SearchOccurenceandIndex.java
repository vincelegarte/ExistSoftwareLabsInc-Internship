package com.homework.one;

public class SearchOccurenceandIndex {
	
	public void findIndex(String table[][], int row, int col, String search) {
        int index = 0;
        int occurence = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                while (true) {
                    index = table[i][j].indexOf(search, index);
                    if (index != -1) {
                        index += table[i][j].length();
                        System.out.println("Output: [" + i + "," + j + "] Occurence " + findOccurence(table[i][j], search));
                    } else {
                        break;
                    }
                }
            }
        }
    }
	
	public int findOccurence(String str, String search) {
        int index = 0;
        int occurence = 0;

        while (index != -1) {
            index = str.indexOf(search, index);
            if (index != -1) {
                occurence++;
                index += search.length();
            }
        }
        return occurence;
    }
	
}