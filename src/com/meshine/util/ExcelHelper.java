package com.meshine.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "lib";
    public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
    public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";
	
    /**
     * Read names from Excel file
     * @param path
     * @return
     * @throws IOException
     */
    public List<String> readNamesFormExcel(String path) throws IOException{
    	if(path == null || EMPTY.equals(path)){
    		return null;
    	}else{
    		String postfix = getPostfix(path);
    		if(!EMPTY.equals(postfix)){
    			if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
    				return readXls(path);
    			}
    			if(OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
    				return readXlsx(path);
    			}
    		}else {
				System.out.println(path + NOT_EXCEL_FILE); 
			}
    	}
    	
    	return null;
    	
    }
    
    
    /**
     * Read the Excel 2010
     * @param path
     * @return
     * @throws IOException
     */
    public List<String> readXlsx(String path) throws IOException{
    	InputStream is = new FileInputStream(path);
    	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    	List<String> names = new ArrayList<>();
    	for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets();numSheet++){
    		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
    		if(xssfSheet == null){
    			continue;
    		}
    		//Read the Row
    		for(int rowNum = 1;rowNum <= xssfSheet.getLastRowNum();rowNum++){
    			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
    			if(xssfRow != null){
    				XSSFCell name = xssfRow.getCell(0);
    				names.add(name.getStringCellValue());
    			}
    		}
    	}
    	return names;
    }
    
    /**
     * Read the Excel 2003-2007
     * @param path
     * @return
     * @throws IOException
     */
    public List<String> readXls(String path) throws IOException{
    	InputStream is = new FileInputStream(path);
    	HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
    	List<String> names = new ArrayList<>();
    	
    	//Read the Sheet
    	for(int numSheet = 0;numSheet < hssfWorkbook.getNumberOfSheets();numSheet++){
    		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
    		if(hssfSheet == null){
    			continue;
    		}
    		//Read the Row
    		for(int rowNum = 1;rowNum<= hssfSheet.getLastRowNum();rowNum++){
    			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
    			if(hssfRow != null){
    				HSSFCell name = hssfRow.getCell(0);
    				names.add(name.getStringCellValue());
    			}
    		}
    	}
    	
    	return names;
    }
    
    public static String getPostfix(String path){
		if(path == null || EMPTY.equals(path.trim())){
			return EMPTY;
		}
		if(path.contains(POINT)){
			return path.substring(path.lastIndexOf(POINT)+1, path.length());
		}
		return EMPTY;
	}
    
    public static void main(String[] args) throws Exception{
		String excel2003_2007 = STUDENT_INFO_XLS_PATH;
		String excel2010 = STUDENT_INFO_XLSX_PATH;
		List<String> list = new ExcelHelper().readNamesFormExcel(excel2003_2007);
		if(list != null){
			System.out.println("Excel2003_2007");
			for(String name : list){
				System.out.println("name::"+name);
			}
		}
		List<String> list1 = new ExcelHelper().readNamesFormExcel(excel2010);
		if(list1 != null){
			System.out.println("Excel2010");
			for(String name : list1){
				System.out.println("name::"+name);
			}
		}
	}
}
