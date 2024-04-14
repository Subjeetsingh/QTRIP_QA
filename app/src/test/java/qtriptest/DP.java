package qtriptest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
public class DP {

    // @DataProvider(name = "userOnboard")
    public static String[][] readExcelFile(String sheetName)  {
        int rowIndex = 0;
         int cellIndex = 0;
         List<List> outputList = new ArrayList<List>();
         try{

        //  FileInputStream excelFile = new FileInputStream("./src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream excelFile = new FileInputStream("/home/crio-user/workspace/shubhjeetrajput-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx");
         Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet(sheetName);
         Iterator<Row> iteratorRow = sheet.rowIterator();

         while (iteratorRow.hasNext()) {
             Row nextRow = iteratorRow.next();
             Iterator<Cell> cellIterator = nextRow.cellIterator();
             List<String> innerList = new ArrayList<String>();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 if (rowIndex > 0 && cellIndex > 0) {
                     if (cell.getCellType() == CellType.STRING) {
                         innerList.add(cell.getStringCellValue());
                     } else if (cell.getCellType() == CellType.NUMERIC) {
                         innerList.add(String.valueOf(cell.getNumericCellValue()));
                     }
                 }
                 cellIndex = cellIndex + 1;
             }
             rowIndex = rowIndex + 1;
             cellIndex = 0;
             if (innerList.size() > 0){
                 outputList.add(innerList);
                 System.out.println(innerList);
         }
        }

         excelFile.close();
        }
         catch(IOException e){
            e.printStackTrace();
         }

         String[][] stringArray = outputList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
         return stringArray;
        }
    }

     
