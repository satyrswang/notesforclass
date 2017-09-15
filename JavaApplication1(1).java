package com.util;

import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;

import jxl.Cell;
 import jxl.Sheet;
 import jxl.Workbook;
 import jxl.read.biff.BiffException;

public class ReadExcel {
  public static void readExcel(String pathname, PrintWriter out) {
   try {
    // 打开文件
    Workbook book = Workbook.getWorkbook(new File(pathname));
    for (int t = 0; t < book.getSheets().length; t++) {
     out.println("

第:" + (t + 1) + "个sheet内容为:
");
     // 取得第i个sheet
     Sheet sheet = book.getSheet(t);
     // 取得行数
     int rows = sheet.getRows();
     for (int i = 0; i < rows; i++) {
      Cell[] cell = sheet.getRow(i);
      for (int j = 0; j < cell.length; j++) {
       // getCell(列，行)
       out.print(sheet.getCell(j, i).getContents());
       out.print("   ");
      }
      out.println("
 ");
     }
    }
    // 关闭文件
    book.close();
   } catch (BiffException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
  } 
}
