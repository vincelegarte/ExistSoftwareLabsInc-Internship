package com.homework.two;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public void saveFile(String[][] table, int row, int col, String filePath, String fileName) {
        String holder;
        String[][] duplicate = new String[row][col];
        String path = filePath + "\\" + fileName + ".txt";
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
                        if (duplicate[i][j] != null) {
                            if (j == col - 1) {
                                writer.write(duplicate[i][j]);
                            } else {
                                writer.write(duplicate[i][j] + " ");
                            }
                        } else {
                            if (j == col - 1) {
                                writer.write("null ");
                            } else {
                                writer.write("null ");
                            }
                        }
                    }
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid file path.");
        } finally {
            System.out.println("File Name: " + fileName + ".txt");
            System.out.println("File Path: " + path);
        }

    }
}
