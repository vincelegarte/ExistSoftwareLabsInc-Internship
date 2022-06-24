package com.homework.four;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;

public class MainClass {

    private static final Logger logger = LogManager.getLogger(MainClass.class); 

    public static void main(String[] args) throws FileNotFoundException {

        BasicConfigurator.configure(); 

        Scanner sc = new Scanner(System.in);

        Table t = new Table();
        ReadFile rf = new ReadFile();
        CreateTable ct = new CreateTable();
        ShowTable st = new ShowTable();
        SearchTable sct = new SearchTable();
        EditTable et = new EditTable();
        SortTable srt = new SortTable();
        WriteFile wf = new WriteFile();

        String[][] table;
        int start;
        int menu;
        int row;
        int col;
        int editRow;
        int editCol;
        int choice;
        int position;
        String[] arrKey;
        String[] arrVal;
        String key;
        String val;
        String changeVal;
        String newVal;
        boolean flag = true;
        String menuError = "Please only enter a number from the menu:";
        String inputError = "Enter a number only:";

        while (true) {

            while (flag){
                logger.info("1. Open File");
                logger.info("2. Create New Table");
                logger.info("3. Exit");
                logger.info("Select a process: ");
                String tempStart = sc.nextLine();
                while (!StringUtils.isNumeric(tempStart)||(Integer.parseInt(tempStart) < 1 || Integer.parseInt(tempStart) > 3)){
                    logger.info(menuError);
                    tempStart = sc.nextLine();
                }
                start = Integer.parseInt(tempStart);

                switch(start){
                    case 1:
                        try {
                            logger.info("Open File");
                            logger.info("Enter the file path: ");
                            String filePath = sc.nextLine();
                            t.setTable(rf.openFile(filePath));
                            logger.info("\n"+st.printTable(t.getTable()));
                            flag = false;
                        } catch (FileNotFoundException e) {
                            logger.info("File not found!");
                            flag = true;
                        } catch (ArithmeticException e) {
                            logger.info("Invalid file!");
                            flag = true;
                        }
                        break;
                    case 2:
                        logger.info("Enter the number of rows: ");
                        String tempRow = sc.nextLine();
                        while(!StringUtils.isNumeric(tempRow)){
                            logger.info(inputError);
                            tempRow = sc.nextLine();
                        }
                        row = Integer.parseInt(tempRow);
                        logger.info("Enter the number of columns: ");
                        String tempCol = sc.nextLine();
                        while(!StringUtils.isNumeric(tempCol)){
                            logger.info(inputError);
                            tempCol = sc.nextLine();
                        }
                        col = Integer.parseInt(tempCol);

                        table = new String[row][col];

                        t.setTable(ct.stringTable(table));
                        logger.info("\n"+st.printTable(t.getTable()));
                        flag = false;
                        break;
                    case 3:
                        logger.info("Thank you");
                        System.exit(0);
                        break;
                    default:
                        logger.info(menuError);
                }
            }

            String rowRangeError = "Only a number from 0 to "+(t.getTable().length-1)+" can be entered:";
            String colRangeError = "Only a number from 0 to "+(t.getTable()[0].length-1)+" can be entered:";

            logger.info("1. Search");
            logger.info("2. Edit");
            logger.info("3. Sort Table");
            logger.info("4. Add Column");
            logger.info("5. Save");
            logger.info("6. Reset");
            logger.info("7. Exit");
            logger.info("Enter your choice: ");
			String tempMenu = sc.nextLine();
			while (!StringUtils.isNumeric(tempMenu)||(Integer.parseInt(tempMenu) < 1 || Integer.parseInt(tempMenu) > 7)){
				logger.info(menuError);
				tempMenu = sc.nextLine();
			}
            menu = Integer.parseInt(tempMenu);
            
            switch (menu) {

                case 1:
                    logger.info("\n"+st.printTable(t.getTable()));
                    logger.info("Search: ");
                    String search = sc.nextLine();

                    logger.info("\n"+sct.searchOccurenceandIndex(t.getTable(),search));
                    break;
                case 2:
                    logger.info("\n"+st.printTable(t.getTable()));
                    logger.info("Edit (key/value): ");
                    changeVal = sc.nextLine();
                    while (!(StringUtils.equalsAny(changeVal,"key","value"))){
                        logger.info("Enter only 'key' or 'value': ");
                        changeVal = sc.nextLine();
                    }
                    logger.info("Edit (row position): ");
					String tempEditRow = sc.nextLine();
					while (!StringUtils.isNumeric(tempEditRow) || (Integer.parseInt(tempEditRow) < 0 || Integer.parseInt(tempEditRow) > (t.getTable().length-1))) {
						logger.info(rowRangeError);
						tempEditRow = sc.nextLine();
					}
					editRow = Integer.parseInt(tempEditRow);
                    logger.info("Edit (column position): ");
					String tempEditCol = sc.nextLine();
					while (!StringUtils.isNumeric(tempEditCol) || (Integer.parseInt(tempEditCol) < 0 || Integer.parseInt(tempEditCol) > (t.getTable()[0].length-1))) {
						logger.info(colRangeError);
						tempEditCol = sc.nextLine();
					}
                    editCol = Integer.parseInt(tempEditCol);
                    logger.info("New "+changeVal+": ");
                    newVal = sc.nextLine();
                    while (newVal.length() != 3) {
                        logger.info("The "+changeVal+" should be three characters long: ");
                        newVal = sc.nextLine();
                    }
                    t.setTable(et.changeValues(t.getTable(), editRow, editCol, changeVal, newVal));
                    logger.info("\n"+st.printTable(t.getTable()));
                    break;
                case 3:
                    logger.info("\n"+st.printTable(t.getTable()));
                    logger.info("Sort Table");
                    logger.info("Enter 1 for Ascending");
                    logger.info("Enter 2 for Descending");
                    logger.info("Enter your choice: ");
					String tempChoice = sc.nextLine();
					while (!StringUtils.isNumeric(tempChoice) || !(Integer.parseInt(tempChoice) == 1 || Integer.parseInt(tempChoice) == 2)) {
						logger.info("Enter only '1' or '2': ");
						tempChoice= sc.nextLine();
					}
					choice = Integer.parseInt(tempChoice);
                    if (choice == 1) {
                        t.setTable(srt.ascendingSort(t.getTable()));
                        logger.info("\n"+st.printTable(t.getTable()));
                    } else if (choice == 2) {
                        t.setTable(srt.descendingSort(t.getTable()));
                        logger.info("\n"+st.printTable(t.getTable()));
                    }
                    break;
                case 4:
                    logger.info("\n"+st.printTable(t.getTable()));
                    logger.info("Add Column");
                    logger.info("Enter row number: ");
					String tempPosition = sc.nextLine();
					while (!StringUtils.isNumeric(tempPosition) || (Integer.parseInt(tempPosition) < 0 || Integer.parseInt(tempPosition) > (t.getTable().length-1))) {
						logger.info(rowRangeError);
						tempPosition = sc.nextLine();
					}
                    position = Integer.parseInt(tempPosition);
                    logger.info("Enter key: ");
                    key = sc.nextLine();
                    while (StringUtils.length(key) != 3) {
                        logger.info("The key should be three characters long: ");
                        key = sc.nextLine();
                    }
                    logger.info("Enter value: ");
                    val = sc.nextLine();
                    while (StringUtils.length(val) != 3) {
                        logger.info("The value should be three characters long: ");
                        val = sc.nextLine();
                    }

                    arrKey = new String[t.getTable().length];
                    arrVal = new String[t.getTable().length];

                    t.setTable(ct.addColumn(t.getTable(), position, key, val, arrKey, arrVal));
                    logger.info("\n"+st.printTable(t.getTable()));
                    break;
                case 5:
                    logger.info("Save Table");
                    logger.info("Enter file name: ");
                    String fileName = sc.nextLine();
                    logger.info("Enter file path: ");
                    String filePath = sc.nextLine();
                    if(wf.saveFile(t.getTable(), filePath+"\\", fileName)){
                        logger.info("File saved.");
                        logger.info("File Name: " + fileName + ".txt");
                        logger.info("File Path: " + filePath);
                    } else {
                        logger.info("Invalid file path.");
                        logger.info("Saving failed.");
                    }
                    logger.info("\n"+st.printTable(t.getTable()));
                    break;
                case 6:
                    logger.info("Reset");
                    logger.info("Enter the number of rows: ");
					String tempRow = sc.nextLine();
					while (!StringUtils.isNumeric(tempRow)){
						logger.info(inputError);
						tempRow = sc.nextLine();
					}
                    row = Integer.parseInt(tempRow);
                    logger.info("Enter the number of columns: ");
                    String tempCol = sc.nextLine();
					while (!StringUtils.isNumeric(tempCol)){
						logger.info(inputError);
						tempCol = sc.nextLine();
					}
                    col = Integer.parseInt(tempCol);

                    table = new String[row][col];

                    t.setTable(ct.stringTable(table));
                    logger.info("\n"+st.printTable(t.getTable()));
                    break;
                case 7:
                    logger.info("Thank you");
                    System.exit(0);
                    break;
                default:
                    logger.info(menuError);
            }
        }
    }
}
