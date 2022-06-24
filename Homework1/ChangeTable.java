package com.homework.one;

public class ChangeTable{

	public String[][] editTable(String[][] table, int row, int col, int editRow, int editCol, String newVal){
		table[editRow][editCol] = newVal;
		return table;
	}

}