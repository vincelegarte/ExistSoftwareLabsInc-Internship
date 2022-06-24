package com.homework.two;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
        int menu, row, col, editRow, editCol, choice, position;
        String arrKey[], arrVal[];
        String key, val, changeVal, newVal;

        try {
            System.out.println("Open File");
            System.out.print("Enter the file path: ");
            String filePath = sc.nextLine();
            rf.OpenFile(filePath);
            t.setTable(rf.readTable);
            t.setRow(rf.readRow);
            t.setCol(rf.readCol);
            st.printTable(t.getTable(), t.getRow(), t.getCol());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.print("Enter the number of rows: ");
            row = sc.nextInt();
            t.setRow(row);
            System.out.print("Enter the number of columns: ");
            col = sc.nextInt();
            t.setCol(col);

            table = new String[t.getRow()][t.getCol()];
            t.setTable(table);

            ct.stringTable(pairs, t.getTable(), t.getRow(), t.getCol());
            st.printTable(t.getTable(), t.getRow(), t.getCol());
        }

        while (true) {

            System.out.println("1. Search");
            System.out.println("2. Edit");
            System.out.println("3. Sort Table");
            System.out.println("4. Add Column");
            System.out.println("5. Save");
            System.out.println("6. Reset");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            menu = sc.nextInt();
            
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
                    while (!(changeVal.equals("key") || changeVal.equals("value"))){
                        System.out.print("Enter only 'key' or 'value': ");
                        changeVal = sc.next();
                    }
                    System.out.print("Edit (row position): ");
                    editRow = sc.nextInt();
                    while (editRow < 0 || editRow > (t.getRow()-1)) {
                        System.out.print("The row position must be in the range of 0-"+(t.getRow()-1)+": ");
                        editRow = sc.nextInt();
                    }
                    System.out.print("Edit (column position): ");
                    editCol = sc.nextInt();
                    while (editCol < 0 || editCol > (t.getCol()-1)) {
                        System.out.print("The column position must be in the range of 0-"+(t.getCol()-1)+": ");
                        editCol = sc.nextInt();
                    }
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
                    choice = sc.nextInt();
                    while (!(choice == 1 || choice == 2)){
                        System.out.print("Enter only '1' or '2': ");
                        choice = sc.nextInt();
                    }
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
                    position = sc.nextInt();
                    while (position < 0 || position > (t.getRow()-1)) {
                        System.out.print("The row number must be in the range of 0-"+(t.getRow()-1)+": ");
                        position = sc.nextInt();
                    }
                    System.out.print("Enter key: ");
                    key = sc.next();
                    while (key.length() != 3) {
                        System.out.print("The key should be three characters long: ");
                        key = sc.next();
                    }
                    System.out.print("Enter value: ");
                    val = sc.next();
                    while (val.length() != 3) {
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
                    row = sc.nextInt();
                    t.setRow(row);
                    System.out.print("Enter the number of columns: ");
                    col = sc.nextInt();
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
                default:
                    System.out.println("Choice invalid");
            }
        }
    }
}