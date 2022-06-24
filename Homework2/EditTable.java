package com.homework.two;

public class EditTable {
    
    ShowTable st = new ShowTable();
    Table t = new Table();
    
    public void changeValues(String[][] table, int row, int col, String changeVal, String newVal){
        
        if (changeVal.equals("key")){
            try {
                if (table[row][col].contains("null,")){
                    String replace = table[row][col].replace("null,", newVal+",");
                    table[row][col] = replace;
                } else if (table[row][col].contains("null")){
                    table[row][col] = newVal+","+null;
                } else {
                    String replace = table[row][col].replace(table[row][col].substring(0, 4), newVal+",");
                    table[row][col] = replace;
                }
            } catch (NullPointerException e) {
                table[row][col] = newVal+","+null;
            }
        } else if (changeVal.equals("value")){
            try {
                if (table[row][col].contains(",null")){
                    String replace = table[row][col].replace(",null", ","+newVal);
                    table[row][col] = replace;
                } else if (table[row][col].contains("null")){
                    table[row][col] = null+","+newVal;
                } else {
                    String replace = table[row][col].replace(table[row][col].substring(3, 7), ","+newVal);
                    table[row][col] = replace;
                }
            } catch (NullPointerException e){
                table[row][col] = null+","+newVal;
            }
        }
        
        t.setTable(table);
    }
}
