package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-31.
 */
public class ReadExcel {
    public  String getExcel(String filePath,String sheetName,String colmName,int caseId) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(filePath+".xls");
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet(sheetName);
        Row titles = sheet.getRow(0);
        int colNum = titles.getPhysicalNumberOfCells();
        String[] colName = new String[colNum];
        Iterator<Cell> itr = titles.cellIterator();
        int count = 0;
        while (itr.hasNext()){
            Cell cell = itr.next();
            cell.setCellType(CellType.STRING);
            String title = cell.getStringCellValue();
            colName[count] = title;
            count++;
        }
        int col = 0;
        boolean flag = false;
        for (int i = 0;i<colNum;i++){
            String value = colName[i];
            if (value.equals(colmName)){
                col = i;
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.println("没有找到匹配项");
            return null;
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet.getRow(caseId).getCell(col).getStringCellValue();
    }

    public  Map<String, String> getExcle(String filePath,String sheetName,int caseID) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(filePath+".xls");
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet(sheetName);
        Row titles = sheet.getRow(0);
        int colNum = titles.getPhysicalNumberOfCells();
        String[] colName = new String[colNum];
        Iterator<Cell> itr = titles.cellIterator();
        int count = 0;
        while (itr.hasNext()){
            Cell cell = itr.next();
            cell.setCellType(CellType.STRING);
            String title = cell.getStringCellValue();
            colName[count] = title;
            count++;
        }
        Map<String,String> map =new HashMap<String, String>();
        for (int i=0;i<colNum;i++){
            Cell cell = sheet.getRow(caseID).getCell(i);
            cell.setCellType(CellType.STRING);
            map.put(colName[i],cell.getStringCellValue());
        }
        return map;
    }

}