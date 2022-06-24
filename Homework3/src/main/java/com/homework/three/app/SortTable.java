package com.homework.three.app;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang3.ArraySorter;

public class SortTable {

    ShowTable st = new ShowTable();
    Table t = new Table();

    public void ascendingSort(String[][] table, int row, int col) {

        for (int i = 0; i < table.length; i++) {
            ArraySorter.sort(table[i], Comparator.nullsLast(Comparator.naturalOrder()));
        }

        t.setTable(table);
        st.printTable(t.getTable(), row, col);
    }

    public void descendingSort(String[][] table, int row, int col) {

        for (int i = 0; i < table.length; i++) {
            ArraySorter.sort(table[i], Collections.reverseOrder((Comparator<String>)Comparator.nullsFirst(Comparator.naturalOrder())));
        }
    
        t.setTable(table);
        st.printTable(t.getTable(), row, col);
    }

}