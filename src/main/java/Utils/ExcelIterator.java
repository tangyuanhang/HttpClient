package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-31.
 */
public class ExcelIterator implements Iterator<Object[]> {
    private int colNum;  //有效列
    private int rowNum;  //有效行
    private String columnName[]; //表头数组
    private int curRowNum = 0;       //当前行
    private Workbook book;
    private Sheet sheet;
    public ExcelIterator(String filePath, String sheetName) throws IOException {
         readExcel(filePath,sheetName);
    }

    public void readExcel(String fileName,String sheetName) throws IOException {
        //创建一个文件流
        FileInputStream inputStream = new FileInputStream(fileName+".xls");
        //创建工作区间
        book = new HSSFWorkbook(inputStream);
        //获取工作表
        sheet = book.getSheet(sheetName);
        Row row = sheet.getRow(0);
        colNum = row.getPhysicalNumberOfCells();
        rowNum = sheet.getPhysicalNumberOfRows();
        columnName = new String[colNum];
        Iterator<Cell> cellItr = row.cellIterator();
        int count = 0;
        while (cellItr.hasNext()){
            Cell cell = cellItr.next();
            if (cell!=null){
            cell.setCellType(CellType.STRING);
            columnName[count] = cell.getStringCellValue().toString();
            count++;
            }
        }
        inputStream.close();
        this.curRowNum++;
    }

    public boolean hasNext() {
        if (rowNum == 0 || curRowNum>=rowNum){
            return false;
        }else {
            return true;
        }
    }

    public Object[] next() {
        Map<String,String> map = new HashMap();
        String temp;
        Row row = sheet.getRow(curRowNum);
        for (int i =0;i<colNum;i++){
            Cell cell = row.getCell(i);
            if (cell !=null){
            cell.setCellType(CellType.STRING);
            temp = cell.getStringCellValue();
            map.put(columnName[i],temp);
            }
        }
        this.curRowNum++;
        Object[] objects = new Object[1];
        objects[0] = map;
        return objects;
    }

    public void remove() {

    }
}
