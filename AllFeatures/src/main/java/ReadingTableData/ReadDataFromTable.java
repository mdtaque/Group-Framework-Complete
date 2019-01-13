package ReadingTableData;

import base.CommonAPI;
import reporting.TestLogger;

public class ReadDataFromTable extends CommonAPI {

    public String readTable(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        //List<String> list = getTextFromWebElements(".w3-table-all notranslate");
        String cellData = getTextByCss(".w3-table-all.notranslate tr:nth-child(4) td:nth-child(4)");
//        for (int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }
        System.out.println(cellData);
        return cellData;
    }
}
