package com.homework.four;
import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WriteFileTest{

    WriteFile mwf = mock(WriteFile.class);
    WriteFile twf = new WriteFile();

    String name = "test-file";

    String[][] table = {
        {"0:),334",null,"443,dds"},
        {">:c,$$f","POf,null",">>:,ggf"},
        {"^_^,_fT","null,43G","null,556"}};

    @Test
    public void testSaveFile(){
        String validFilePath = "../../Homework4/service/src/test/java/com/homework/four/";
        when(mwf.saveFile(table,validFilePath,name)).thenReturn(true);
        assertTrue(twf.saveFile(table,validFilePath,name));
    }

    @Test
    public void testInvalidFilePath() throws IOException{
        String invalidFilePath = "ab/cd/ef/gh";
        when(mwf.saveFile(table,invalidFilePath,name)).thenReturn(false);
        assertFalse(twf.saveFile(table,invalidFilePath,name));
    }
}