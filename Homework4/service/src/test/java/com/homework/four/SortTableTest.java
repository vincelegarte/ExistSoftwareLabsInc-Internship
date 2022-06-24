package com.homework.four;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SortTableTest{

    SortTable msrt = mock(SortTable.class);
    SortTable tsrt = new SortTable();

    String[][] table = {
        {"0:),334",null,"443,dds"},
        {">:c,$$f","POf,null",">>:,ggf"},
        {"^_^,_fT","null,43G","null,556"}};

    @Test
    public void testAscendingTable(){
        String[][] expectedTable = {
            {"0:),334","443,dds",null},
            {">:c,$$f",">>:,ggf","POf,null"},
            {"^_^,_fT","null,43G","null,556"}};
        when(msrt.ascendingSort(table)).thenReturn(expectedTable);
        assertArrayEquals(expectedTable,tsrt.ascendingSort(table));
    }

    @Test
    public void testAescendingTable(){
        String[][] expectedTable = {
            {"443,dds","0:),334",null},
            {"POf,null",">>:,ggf",">:c,$$f"},
            {"null,556","null,43G","^_^,_fT"}};
        when(msrt.descendingSort(table)).thenReturn(expectedTable);
        assertArrayEquals(expectedTable,tsrt.descendingSort(table));
    }

}