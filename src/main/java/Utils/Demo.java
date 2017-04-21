package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ${user} on 2017-04-12.
 */
public class Demo implements Iterator<Object[]> {
    private int curRowNum = 0;
    private Sheet sheet;
    private int colNum;
    private int rowNum;
    private String titles[];
    private void read(String filePath,String sheetName) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath+".xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(0);
        colNum = row.getPhysicalNumberOfCells();
        rowNum = sheet.getPhysicalNumberOfRows();
        titles= new String[colNum];
        for (int i=0;i<colNum;i++){
            Cell cell = row.getCell(i);
            if (cell !=null){
                cell.setCellType(CellType.STRING);
                titles[i] = cell.getStringCellValue();
            }else
                System.out.println("单元格为空");
        }
        curRowNum++;
    }
    public boolean hasNext() {
        if (rowNum==0 || curRowNum<=rowNum){
            return false;
        }else {
            return true;
        }
    }

    public Object[] next() {
        Row row = sheet.getRow(curRowNum);
        Map<String,String>map = new HashMap<String, String>();
        for (int i= 0;i<colNum;i++){
            Cell cell = row.getCell(i);
            cell.setCellType(CellType.STRING);
            map.put(titles[i],cell.getStringCellValue());
        }
        curRowNum++;
        Object[] objects = new Object[1];
        objects[0] = map;
        return objects;
    }

    public void remove() {

    }
}
