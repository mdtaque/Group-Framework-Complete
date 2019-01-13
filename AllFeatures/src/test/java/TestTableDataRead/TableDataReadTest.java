package TestTableDataRead;

import ReadingTableData.ReadDataFromTable;
import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.TestLogger;

public class TableDataReadTest extends ReadDataFromTable {

    @Test
    public void tableRead(){
        driver.get("https://www.w3schools.com/sql/sql_select.asp");
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        readTable();
        Assert.assertEquals("Mataderos 2312",readTable());
    }
}
