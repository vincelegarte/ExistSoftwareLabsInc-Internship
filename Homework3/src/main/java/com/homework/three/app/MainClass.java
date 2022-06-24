package com.homework.three.app;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class MainClass {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        Table t = new Table();
        ReadFile rf = new ReadFile();
        HashMap<String, String> pairs = new HashMap<>();
        CreateTable ct = new CreateTable();
        ShowTable st = new ShowTable();
        SearchTable sct = new SearchTable();
        EditTable et = new EditTable();
        SortTable srt = new SortTable();
        WriteFile wf = new WriteFile();

        String[][] table;
        int start, menu, row, col, editRow, editCol, choice, position;
        String arrKey[], arrVal[];
        String key, val, changeVal, newVal;
        boolean flag = true;

        while (true) {

            while (flag){
                System.out.println("1. Open File");
                System.out.println("2. Create New Table");
                System.out.println("3. Exit");
                System.out.print("Select a process: ");
                String tempStart = sc.next();
                while (!StringUtils.isNumeric(tempStart) || (Integer.parseInt(tempStart) < 1 || Integer.parseInt(tempStart) > 3)){
                    System.out.print("Please only enter a number from the menu: ");
                    tempStart = sc.next();
                }
                start = Integer.parseInt(tempStart);

                switch(start){
                    case 1:
                        try {
                            System.out.println("Open File");
                            System.out.print("Enter the file path: ");
                            String filePath = sc.next();
                            rf.OpenFile(filePath);
                            t.setTable(rf.readTable);
                            t.setRow(rf.readRow);
                            t.setCol(rf.readCol);
                            st.printTable(t.getTable(), t.getRow(), t.getCol());
                            flag = false;
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found!");
                            flag = true;
                        }
                        break;
                    case 2:
                        System.out.print("Enter the number of rows: ");
                        String tempRow = sc.next();
                        while(!StringUtils.isNumeric(tempRow)){
                            System.out.print("Enter a number only: " );
                            tempRow = sc.next();
                        }
                        row = Integer.parseInt(tempRow);
                        t.setRow(row);
                        System.out.print("Enter the number of columns: ");
                        String tempCol = sc.next();
                        while(!StringUtils.isNumeric(tempCol)){
                            System.out.print("Enter a number only: " );
                            tempCol = sc.next();
                        }
                        col = Integer.parseInt(tempCol);
                        t.setCol(col);

                        table = new String[t.getRow()][t.getCol()];
                        t.setTable(table);

                        ct.stringTable(pairs, t.getTable(), t.getRow(), t.getCol());
                        st.printTable(t.getTable(), t.getRow(), t.getCol());
                        flag = false;
                        break;
                    case 3:
                        System.out.print("Thank you");
                        System.exit(0);
                        break;
                }
            }

            System.out.println("1. Search");
            System.out.println("2. Edit");
            System.out.println("3. Sort Table");
            System.out.println("4. Add Column");
            System.out.println("5. Save");
            System.out.println("6. Reset");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
			String tempMenu = sc.next();
			while (!StringUtils.isNumeric(tempMenu) || (Integer.parseInt(tempMenu) < 1 || Integer.parseInt(tempMenu) > 7)){
				System.out.print("Please only enter a number from the menu: ");
				tempMenu = sc.next();
			}
            menu = Integer.parseInt(tempMenu);
            
            switch (menu) {

                case 1:
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    System.out.print("Search: ");
                    String search = sc.next();

                    sct.OccurenceandIndex(t.getTable(), t.getRow(), t.getCol(), search);
                    break;
                case 2:
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    System.out.print("Edit (key/value): ");
                    changeVal = sc.next();
                    while (!(StringUtils.equalsAny(changeVal,"key","value"))){
                        System.out.print("Enter only 'key' or 'value': ");
                        changeVal = sc.next();
                    }
                    System.out.print("Edit (row position): ");
					String tempEditRow = sc.next();
					while (!StringUtils.isNumeric(tempEditRow) || (Integer.parseInt(tempEditRow) < 0 || Integer.parseInt(tempEditRow) > (t.getRow()-1))) {
						System.out.print("Only a number from 0 to "+(t.getRow()-1)+" can be entered: ");
						tempEditRow = sc.next();
					}
					editRow = Integer.parseInt(tempEditRow);
                    System.out.print("Edit (column position): ");
					String tempEditCol = sc.next();
					while (!StringUtils.isNumeric(tempEditCol) || (Integer.parseInt(tempEditCol) < 0 || Integer.parseInt(tempEditCol) > (t.getCol()-1))) {
						System.out.print("Only a number from 0 to "+(t.getCol()-1)+" can be entered: ");
						tempEditCol = sc.next();
					}
                    editCol = Integer.parseInt(tempEditCol);
                    System.out.print("New "+changeVal+": ");
                    newVal = sc.next();
                    while (newVal.length() != 3) {
                        System.out.print("The "+changeVal+" should be three characters long: ");
                        newVal = sc.next();
                    }
                    et.changeValues(t.getTable(), editRow, editCol, changeVal, newVal);
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    break;
                case 3:
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    System.out.println("Sort Table");
                    System.out.println("Enter 1 for Ascending");
                    System.out.println("Enter 2 for Descending");
                    System.out.print("Choice: ");
					String tempChoice = sc.next();
					while (!StringUtils.isNumeric(tempChoice) || !(Integer.parseInt(tempChoice) == 1 || Integer.parseInt(tempChoice) == 2)) {
						System.out.print("Enter only '1' or '2': ");
						tempChoice= sc.next();
					}
					choice = Integer.parseInt(tempChoice);
                    if (choice == 1) {
                        srt.ascendingSort(t.getTable(), t.getRow(), t.getCol());
                    } else if (choice == 2) {
                        srt.descendingSort(t.getTable(), t.getRow(), t.getCol());
                    }
                    break;
                case 4:
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    System.out.println("Add Column");
                    System.out.print("Enter row number: ");
					String tempPosition = sc.next();
					while (!StringUtils.isNumeric(tempPosition) || (Integer.parseInt(tempPosition) < 0 || Integer.parseInt(tempPosition) > (t.getRow()-1))) {
						System.out.print("Only a number from 0 to "+(t.getRow()-1)+" can be entered: ");
						tempPosition = sc.next();
					}
                    position = Integer.parseInt(tempPosition);
                    System.out.print("Enter key: ");
                    key = sc.next();
                    while (StringUtils.length(key) != 3) {
                        System.out.print("The key should be three characters long: ");
                        key = sc.next();
                    }
                    System.out.print("Enter value: ");
                    val = sc.next();
                    while (StringUtils.length(val) != 3) {
                        System.out.print("The value should be three characters long: ");
                        val = sc.next();
                    }

                    arrKey = new String[t.getRow()];
                    arrVal = new String[t.getRow()];

                    ct.addColumn(t.getTable(), t.getRow(), t.getCol(), position, key, val, arrKey, arrVal);
                    t.setCol(ct.addCol);
                    t.setTable(ct.newTable);
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    break;
                case 5:
                    System.out.print("Enter file name: ");
                    String fileName = sc.next();
                    System.out.print("Enter file path: ");
                    String filePath = sc.next();
                    wf.saveFile(t.getTable(), t.getRow(), t.getCol(), filePath, fileName);
                    break;
                case 6:
                    System.out.print("Enter the number of rows: ");
					String tempRow = sc.next();
					while (!StringUtils.isNumeric(tempRow)){
						System.out.print("Enter a number only: ");
						tempRow = sc.next();
					}
                    row = Integer.parseInt(tempRow);
                    t.setRow(row);
                    System.out.print("Enter the number of columns: ");
                    String tempCol = sc.next();
					while (!StringUtils.isNumeric(tempCol)){
						System.out.print("Enter a number only: ");
						tempCol = sc.next();
					}
                    col = Integer.parseInt(tempCol);
                    t.setCol(col);

                    table = new String[t.getRow()][t.getCol()];
                    t.setTable(table);

                    ct.stringTable(pairs, t.getTable(), t.getRow(), t.getCol());
                    st.printTable(t.getTable(), t.getRow(), t.getCol());
                    break;
                case 7:
                    System.out.print("Thank you");
                    System.exit(0);
                    break;
            }
        }
    }
}
