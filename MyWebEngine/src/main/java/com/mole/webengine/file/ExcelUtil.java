package com.mole.webengine.file;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class ExcelUtil {
	
	//readValue
	public static Object getValue(HSSFCell cell){
        if(cell == null){
            return "";
        }
		Object obj = null;
		switch(cell.getCellType()){
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				obj = cell.getDateCellValue();
			} 
			else{
				obj = Double.valueOf(cell.getNumericCellValue());	
			}	
			break;
		case HSSFCell.CELL_TYPE_STRING:
			//obj = cell.getRichStringCellValue();
			obj = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			obj = cell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			obj = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			obj = cell.getErrorCellValue();
			break;
		default:
			obj = cell.getStringCellValue();
			break;
		}
		return obj;		
	}
	//getString
	public static String getString(HSSFCell cell) {
		return getValue(cell).toString();		
	}
	//getDate
	public static Date getDate(HSSFCell cell) {	
		return (Date)getValue(cell);
	}
	//getDouble
	public static Double getDouble(HSSFCell cell){
		String str = getValue(cell).toString();
		if(str.equals("")){
			return 0.0;
		}
		return Double.valueOf(getValue(cell).toString());	
	}
	//getLong
	public static Long getLong(HSSFCell cell){
		return getDouble(cell).longValue();
	}
	//getInteger
	public static Integer getInteger(HSSFCell cell){
		return getDouble(cell).intValue();
	}
	//getFloat
	public static Float Float(HSSFCell cell){
		return getDouble(cell).floatValue();
	}
	
}
