package com.homework.four;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowTableTest {

    ShowTable mst = mock(ShowTable.class);
    ShowTable tst = new ShowTable();

    String[][] table = {
        {"0:),334",null},
        {">:c,$$f","POf,null"},
        {"^_^,_fT","null,43G"}};
        
    @Test
    public void testPrintTable(){
        String expectedOutput = "[0:),334] [null] \n[>:c,$$f] [POf,null] \n[^_^,_fT] [null,43G] \n";
        when(mst.printTable(table)).thenReturn(expectedOutput);
        assertEquals(expectedOutput,tst.printTable(table));
    }

}