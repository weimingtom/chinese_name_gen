package com.iteye.weimingtom.namegen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class NameXlsGen {
	public static void main(String[] args) {
		final String filename = "demo.xls";
		final int genTotal = 2500;
	    final String[] headers = {
    		"姓名", 
    		"性别", 
	    };
	    
		NameGen nameGen = new NameGen();
		Workbook wb = new HSSFWorkbook();
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            
            Sheet sheet = wb.createSheet("Chinese Name Gen");
            Row row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                row.createCell(i).setCellValue(headers[i]);
            }
            long s1 = System.nanoTime();
            int r = 1;
            for (int i = 0; i < genTotal; i++) {
                row = sheet.createRow(r);
                String name = nameGen.getName();
                row.createCell(0).setCellValue(name);
                row.createCell(1).setCellValue(nameGen.getSex(name));
                r++;
            }
            wb.write(fileOut);
            fileOut.close();
            long s2 = System.nanoTime();
            System.out.println("poi write " + r + " rows to excel:" + (s2-s1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
