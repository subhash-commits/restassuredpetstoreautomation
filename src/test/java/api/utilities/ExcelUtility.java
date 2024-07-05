package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	FileInputStream fin;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String path = null;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowcount(String sheetname) throws IOException {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetname);
		int rows = sheet.getLastRowNum();
		fin.close();
		return rows;

	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fin.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int columnnum) throws IOException {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(columnnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of
													// the cell type.
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fin.close();
		return data;
	}

	public void setCellData(String data, int rownum, int cellnum, String sheetname) throws IOException {

		File file = new File(path);
		if (!file.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		}

		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		if (workbook.getSheetIndex(sheetname) == -1)// If sheet not exists then create new Sheet
			workbook.createSheet(sheetname);
		sheet = workbook.getSheet(sheetname);

		if (sheet.getRow(rownum) == null)// If row not exists then create new Row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(cellnum);
		DataFormatter formatter = new DataFormatter();
		cell.setCellValue(data);

		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fos.close();
		fin.close();

	}
}
