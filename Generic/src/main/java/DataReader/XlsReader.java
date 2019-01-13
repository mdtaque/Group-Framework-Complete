package DataReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XlsReader {

    public static String Path = "/Users/mdtaque/eclipse-workspace/GroupFramework/ebay/src/test/java/DataDrivenTest/Xls_To_Read.xls";
    static Workbook book;

    static Sheet sheet;

    public static Object[][] getTestData(int sheetIndex){
        FileInputStream file = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(Path));
            //file = new FileInputStream(Path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(bis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        sheet = book.getSheetAt(sheetIndex);
        //Object[][] data = new  Object[sheet.getLastRowNum()+1][sheet.getRow(0).getLastCellNum()+1];
        Object [][] parsedData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0;i<parsedData.length;i++){
            for (int k = 0; k < sheet.getRow(i).getLastCellNum();k++){
                parsedData[i][k] = sheet.getRow(i+1).getCell(k).toString();
            }
        }
        return parsedData;
    }
}
