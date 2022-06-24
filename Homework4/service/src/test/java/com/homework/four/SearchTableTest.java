package com.homework.four;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchTableTest{

    SearchTable msct = mock(SearchTable.class);
    SearchTable tsct = new SearchTable();

    String[][] table = {
        {"0:),334",null,},
        {">:c,$::","00f,kll"},
        {"^_^,null","null,434"}};

    @Test
    public void testMatchesKey(){
        String expectedOutput = "Output: [1,0] - 1 Occurence found on key field\n";
        when(msct.searchOccurenceandIndex(table,">")).thenReturn(expectedOutput);
        assertEquals(expectedOutput,tsct.searchOccurenceandIndex(table,">"));
    }

    @Test
    public void testMatchesVal(){
        String expectedOutput = "Output: [0,0] - 1 Occurence found on value field\nOutput: [2,1] - 2 Occurence found on value field\n";
        when(msct.searchOccurenceandIndex(table,"4")).thenReturn(expectedOutput);
        assertEquals(expectedOutput,tsct.searchOccurenceandIndex(table,"4"));
    }

    @Test
    public void testMatchesValandKey(){
        String expectedOutput = "Output: [0,0] - 1 Occurence found on key field\nOutput: [1,0] - 1 Occurence found on key field\nOutput: [1,0] - 2 Occurence found on value field\n";
        when(msct.searchOccurenceandIndex(table,":")).thenReturn(expectedOutput);
        assertEquals(expectedOutput,tsct.searchOccurenceandIndex(table,":"));
    }

    @Test
    public void testNoMatch(){
        String expectedOutput = "";
        when(msct.searchOccurenceandIndex(table,"null")).thenReturn(expectedOutput);
        assertEquals(expectedOutput,tsct.searchOccurenceandIndex(table,"null"));
    }

}