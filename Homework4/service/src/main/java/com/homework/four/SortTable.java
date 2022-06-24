package com.homework.four;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang3.ArraySorter;

public class SortTable {

    public String[][] ascendingSort(String[][] table) {

        for (int i = 0; i < table.length; i++) {
            ArraySorter.sort(table[i], Comparator.nullsLast(Comparator.naturalOrder()));
        }

        return table;
    }

    public String[][] descendingSort(String[][] table) {

        for (int i = 0; i < table.length; i++) {
            ArraySorter.sort(table[i], Collections.reverseOrder((Comparator<String>)Comparator.nullsFirst(Comparator.naturalOrder())));
        }

        return table;
    }

}