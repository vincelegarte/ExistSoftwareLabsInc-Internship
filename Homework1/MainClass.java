package com.homework.one;
import java.util.Scanner;

public class MainClass{
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		RandomCharGenerator rcg = new RandomCharGenerator();
		ShowTable st = new ShowTable();
		SearchOccurenceandIndex soi = new SearchOccurenceandIndex();
		ChangeTable ct = new ChangeTable();
		
		int row, col, menu;
		String search;

		System.out.print("Enter the number of row: ");
		row = sc.nextInt();
		System.out.print("Enter the number of column: ");
		col = sc.nextInt();
		String[][] table = new String[row][col];
		
		System.out.println("User Input: "+row+"x"+col);
		rcg.storeString(table,row,col);
		
		st.printTable(table,row,col);
			
		while(true){
			
			System.out.println("1. Search");
            System.out.println("2. Edit");
			System.out.println("3. Reset");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
            menu = sc.nextInt();
			
			switch(menu){
				case 1:
					st.printTable(table,row,col);
					System.out.print("Search: ");
                    search = sc.next();
					soi.findIndex(table,row,col,search);
					break;
				case 2:
					st.printTable(table,row,col);
					System.out.print("Edit (Row Position): ");
					int editRow = sc.nextInt();
					System.out.print("Edit (Column Position): ");
					int editCol = sc.nextInt();
					System.out.println("User Input: "+editRow+"x"+editCol);
					
					System.out.print("New Value: ");
					String newVal = sc.next();
			
					ct.editTable(table, row, col, editRow, editCol, newVal);
					st.printTable(table,row,col);
					break;
				case 3:
					System.out.print("Enter the number of row: ");
					row = sc.nextInt();
					System.out.print("Enter the number of column: ");
					col = sc.nextInt();
					table = new String[row][col];
					
					System.out.println("User Input: "+row+"x"+col);
					rcg.storeString(table,row,col);
					
					st.printTable(table,row,col);
					break;
				case 4:
					System.out.print("Thank you");
                    System.exit(0);
                    break;
				default:
					System.out.println("Choice invalid");
			}
		}
		
	}
	
}