package com.homework.three.app;

class Table {
    
    private String[][] table;
    private int row;
    private int col;
    
    public String[][] getTable(){
        return table;
    }
    
    public void setTable(String[][] table){
        this.table = table;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public int getCol(){
        return col;
    }
    
    public void setCol(int col){
        this.col = col;
    }
    
}