package com.homework.four;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadFileTest{

    ReadFile mrf = mock(ReadFile.class);
    ReadFile trf = new ReadFile();

    @Test
    public void testOpenFile() throws FileNotFoundException{
        String filePath = "../../Homework4/service/src/test/java/com/homework/four/test-file.txt";
        String[][] expectedTable = {
            {"0:),334",null,"443,dds"},
            {">:c,$$f","POf,null",">>:,ggf"},
            {"^_^,_fT","null,43G","null,556"}};
        int expectedRowLength = 3;
        int expectedColLength = 3;
        when(mrf.openFile(filePath)).thenReturn(expectedTable);
        assertEquals(expectedRowLength,trf.openFile(filePath).length);
        assertEquals(expectedColLength,trf.openFile(filePath)[0].length);
        assertArrayEquals(expectedTable,trf.openFile(filePath));
    }

    @Test(expected=ArithmeticException.class)
    public void testEmptyFile() throws FileNotFoundException{
        String filePath = "../../Homework4/service/src/test/java/com/homework/four/empty-file.txt";
        when(mrf.openFile(filePath)).thenThrow(ArithmeticException.class);
        trf.openFile(filePath);
    }
}