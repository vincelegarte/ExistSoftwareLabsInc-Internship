package com.homework.four;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils; 

public class WriteFile {

    public boolean saveFile(String[][] table, String filePath, String fileName) {

        boolean  status = true;
        
        int row = table.length;
        int col = table[0].length;
        
        String holder;
        String[][] duplicate = new String[row][col];
        String path = filePath + fileName + ".txt";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                holder = table[i][j];
                duplicate[i][j] = holder;
            }
        }

        try {
            try (FileWriter writer = new FileWriter(path)) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (StringUtils.isNoneEmpty(duplicate[i][j])) {
                            if (j == col - 1) {
                                writer.write(duplicate[i][j]);
                            } else {
                                writer.write(duplicate[i][j] + " ");
                            }
                        } else {
                            writer.write("null ");
                        }
                    }
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            status = false;
        }
        return status;
    }
}