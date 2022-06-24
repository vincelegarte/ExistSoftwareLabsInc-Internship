package com.homework.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    Table t = new Table();
    ShowTable st = new ShowTable();

    String readTable[][];
    int readRow, readCol;

    public void OpenFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner rowNum = new Scanner(file);
        Scanner colNum = new Scanner(file);
        Scanner textFile = new Scanner(file);
        int countx = 0;
        int county = 0;

        while (rowNum.hasNextLine()) {
            rowNum.nextLine();
            countx++;
        }
        while (colNum.hasNext()) {
            colNum.next();
            county++;
        }

        readRow = countx;
        readCol = county / countx;
        readTable = new String[readRow][readCol];
        String n = "";
        while (textFile.hasNextLine()) {
            for (int i = 0; i < readRow; i++) {
                String[] line = textFile.nextLine().trim().split(" ");
                for (int j = 0; j < readCol; j++) {
                    if (line[j].trim().equals("null")) {
                        readTable[i][j] = null;
                    } else {
                        readTable[i][j] = line[j];
                    }
                }
            }
        }

    }
}
