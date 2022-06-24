package com.homework.four;

import org.junit.Test;  
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class EditTableTest{
   
   EditTable met = mock(EditTable.class);
   EditTable tet = new EditTable();

   String[][] table = {
        {"0:),334",null},
        {">:c,$$f","POf,null"},
        {"^_^,_fT","null,43G"}};
    
    @Test
    public void testChangeKey() {
        String[][] expectedTable = {
            {"new,334",null},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","null,43G"}};
        when(met.changeValues(table,0,0,"key","new")).thenReturn(expectedTable);
        assertEquals("new,334", tet.changeValues(table,0,0,"key","new")[0][0]);
    }

    @Test
    public void testAddKeyandNull() {
        String[][] expectedTable = {
            {"0:),334","new,null"},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","null,43G"}};
        when(met.changeValues(table,0,1,"key","new")).thenReturn(expectedTable);
        assertEquals("new,null", tet.changeValues(table,0,1,"key","new")[0][1]);
    }

   @Test
   public void testChangeNulltoKey() {
        String[][] expectedTable = {
            {"0:),334",null},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","new,43G"}};
        when(met.changeValues(table,2,1,"key","new")).thenReturn(expectedTable);
        assertEquals("new,43G", tet.changeValues(table,2,1,"key","new")[2][1]);
    }

    @Test
    public void testChangeVal() {
        String[][] expectedTable = {
            {"0:),new",null},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","null,43G"}};
        when(met.changeValues(table,0,0,"value","new")).thenReturn(expectedTable);
        assertEquals("0:),new", tet.changeValues(table,0,0,"value","new")[0][0]);
    }

    @Test
    public void testAddNullandVal() {
        String[][] expectedTable = {
            {"0:),334","null,new"},
            {">:c,$$f","POf,null"},
            {"^_^,_fT","null,43G"}};
        when(met.changeValues(table,0,1,"value","new")).thenReturn(expectedTable);
        assertEquals("null,new", tet.changeValues(table,0,1,"value","new")[0][1]);
    }

   @Test
   public void testChangeNulltoVal() {
        String[][] expectedTable = {
            {"0:),334",null},
            {">:c,$$f","POf,new"},
            {"^_^,_fT","null,43G"}};
        when(met.changeValues(table,1,1,"value","new")).thenReturn(expectedTable);
        assertEquals("POf,new", tet.changeValues(table,1,1,"value","new")[1][1]);
    }
}