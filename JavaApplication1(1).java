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
    // ���ļ�
    Workbook book = Workbook.getWorkbook(new File(pathname));
    for (int t = 0; t < book.getSheets().length; t++) {
     out.println("

��:" + (t + 1) + "��sheet����Ϊ:
");
     // ȡ�õ�i��sheet
     Sheet sheet = book.getSheet(t);
     // ȡ������
     int rows = sheet.getRows();
     for (int i = 0; i < rows; i++) {
      Cell[] cell = sheet.getRow(i);
      for (int j = 0; j < cell.length; j++) {
       // getCell(�У���)
       out.print(sheet.getCell(j, i).getContents());
       out.print("   ");
      }
      out.println("
 ");
     }
    }
    // �ر��ļ�
    book.close();
   } catch (BiffException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
  } 
}
