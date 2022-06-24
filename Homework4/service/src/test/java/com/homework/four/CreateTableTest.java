package com.homework.four;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateTableTest{

    CreateTable mct = mock(CreateTable.class);
    CreateTable tct = new CreateTable();

    @Test
    public void testCreateTable(){
        String[][] table = new String[4][5];
        int expectedRowLength = 4;
        int expectedColLength = 5;
        when(mct.stringTable(table)).thenReturn(table);
        assertEquals(expectedRowLength,tct.stringTable(table).length);
        assertEquals(expectedColLength,tct.stringTable(table)[0].length);
    }

    @Test
    public void testAddColumn(){
        String[][] table = {
            {"0:),334",null},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","null,43G"}};
        int position = 1;
        String key = "new";
        String val = "new";
        String[] arrKey = new String[table.length];
        String[] arrVal = new String[table.length];
        String[][] expectedTable = {
            {"0:),334",null,null},
            {">:c,$$f","POf,null","new,new"},
            {"^_^,_fT","null,43G",null}};
        int expectedColLength = 3;
        when(mct.addColumn(table,position,key,val,arrKey,arrVal)).thenReturn(expectedTable);
        assertArrayEquals(expectedTable,tct.addColumn(table,position,key,val,arrKey,arrVal));
        assertEquals(expectedColLength,tct.addColumn(table,position,key,val,arrKey,arrVal)[0].length);
    }
}