package com.PracticeProject.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;




public class ExcelFileReader {
	public ArrayList<JSONObject> readExcelFile() {
		//public void readExcelFile(){
		ArrayList<JSONObject> arrayList=new ArrayList<JSONObject>();
		try {
			FileInputStream file = new FileInputStream(new File("E:/ceino.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			ArrayList<String> headerfield = new ArrayList<String>();

			int i=0;
			for (Row tempRow : sheet)
			{
				JSONObject jsonObject=new JSONObject();
				if(i==0)
				{
					for (int column = 0; column < 4; column++) 
					{

						Cell cell = tempRow.getCell(column);

						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) 
						{
							headerfield.add(cell.getStringCellValue());
						}
					} 
					System.out.println(headerfield);
				}

				else{

					for (int column = 0; column < 4; column++) 
					{
						String header = headerfield.get(column);

						Cell cell1 = tempRow.getCell(column);

						if (cell1.getCellType() == XSSFCell.CELL_TYPE_STRING) 
						{
							jsonObject.put(header, cell1.getStringCellValue());
						} 
						else if (cell1.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						{
							jsonObject.put(header, cell1.getNumericCellValue());
						}



					}
					JSONObject resultJson = new JSONObject();
					resultJson.put("body", jsonObject);
					resultJson.put("path", "/1/classes/Employees");
					resultJson.put("method", "PUT");
					resultJson.put("method", "POST");


					arrayList.add(resultJson);
					//System.out.println("R"+ arrayList);
				}
				i++;
			}
		}


		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;

	}


}

