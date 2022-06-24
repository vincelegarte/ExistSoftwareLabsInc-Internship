package com.homework.four;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils; 

public class ReadFile {

    public String[][] openFile(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);

        int rowCount = 0;
        int colCount = 0;

        String[][] readTable;
        int readRow;
        int readCol;

        try(Scanner rowNum = new Scanner(file)){
            while (rowNum.hasNextLine()) {
                rowNum.nextLine();
                rowCount++;
            }
        }
        try(Scanner colNum = new Scanner(file)){
            while (colNum.hasNext()) {
                colNum.next();
                colCount++;
            }
        }

        if(rowCount<=0){
            throw new ArithmeticException("");
        } else {
            readRow = rowCount;
            readCol = colCount / rowCount;
            readTable = new String[readRow][readCol];
            
            try(Scanner textFile = new Scanner(file)){
                while (textFile.hasNextLine()) {
                    for (int i = 0; i < readRow; i++) {
                        String[] line = textFile.nextLine().trim().split(" ");
                        for (int j = 0; j < readCol; j++) {
                            if(StringUtils.equals(StringUtils.trim(line[j]),"null")){
                                readTable[i][j] = null;
                            } else {
                                readTable[i][j] = line[j];
                            }
                        }
                    }
                }
            }
        }

        return readTable;
		
    }
	
}
