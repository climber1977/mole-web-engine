package com.mole.webengine.dbapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.file.ExcelUtil;


public class ExportToExcel {
	final Logger logger = LoggerFactory.getLogger(ExportToExcel.class);
	private HSSFWorkbook _workbook;
	private HSSFSheet _hssfSheet;
	MyResult _rs;
	OutputStream _saveOut;
	
	//初始化
	private void init(String path, MyJDBC db, String sql) throws Exception{
		FileInputStream fStream = new FileInputStream(path);
		_workbook = new HSSFWorkbook(fStream);
		_hssfSheet = _workbook.getSheetAt(0);
		fStream.close();
		
		_rs = db.queryReturnListList(sql);
	}
	//关闭excel
	private void closeExcel() throws IOException{	
		if(_workbook == null){
			return;
		}
		_workbook.close();
	}
	//保存
	private void save(String path) throws IOException{
		OutputStream fStream = null;
		if(_saveOut != null){
			fStream = _saveOut;
		}else{
			fStream = new FileOutputStream(path);
		}			
		_workbook.write(fStream);
		_workbook.close();
		fStream.close();
	}
	//设置表名
	private void setTableName(){
		MyResultSetMetaData rsmd = _rs.getResultSetMetaData();	
		HSSFRow hssfRow = _hssfSheet.getRow(DBConstants.ROW_NUM_TABLENAME);
		HSSFCell hssCell = hssfRow.getCell(DBConstants.COL_NUM_VALUE_START);
		//支取第一个表
		hssCell.setCellValue(rsmd.getColumnTable(0));
	}
	//设置字段
	private void setFields(){
		MyResultSetMetaData rsmd = _rs.getResultSetMetaData();	
		HSSFRow hssfRow = _hssfSheet.getRow(DBConstants.ROW_NUM_DB_FILED_NAME);
		HSSFCell hssCell;
		for(int i=0; i<_rs.getColumnCount(); i++){
			hssCell = hssfRow.createCell(DBConstants.COL_NUM_VALUE_START+i);
			hssCell.setCellValue(rsmd.getColumnLabel(i));
		}
	}
	//设置数据
	private void setDatas() throws Exception{
		HSSFRow hssfRow;
		HSSFCell hssCell;
		String val = "";
		for(int i=0; i<_rs.getRowCount(); i++){		
			hssfRow = _hssfSheet.createRow(DBConstants.ROW_NUM_DATA_START+i);
			for(int j=0; j<_rs.getColumnCount(); j++){
				hssCell = hssfRow.createCell(DBConstants.COL_NUM_VALUE_START+j);
				
				val = _rs.getString(i, j);
				
				hssCell.setCellValue(val);
			}
		}
	}
	//将所有列，导出到excel
	public boolean export(String path, String templatePaht, MyJDBC db, String sql) throws Exception{
		try {
			init(templatePaht, db, sql);
			setTableName();					
			setFields();
			setDatas();
			save(path);
		} catch (Exception e) {
			throw new MyException(e, "ExportToExcel::export-->path=", path, 
					"\r\n templatePaht=", templatePaht,
					"\r\n dbs=", db.getDBS(),
					"\r\n dataSource=", db.getDataSource().toString(),
					"\r\n sql=", sql
					);
		} finally {
			closeExcel();
		}	
		return true;
	}
	public boolean export(OutputStream out, String templatePaht, MyJDBC db, String sql) throws Exception{
		_saveOut = out;
		return 	export("", templatePaht, db, sql);
	}
	//导出表
	public boolean exportTable(String path, String templatePaht, MyJDBC db, String tableName) throws Exception{
		String sql = "select * from " + tableName;
		return export(path, templatePaht, db, sql);
	}
	//从excel中取得导出字段
	private Vector<ExcelField> getFieldsFromExcel(){
		HSSFRow hssfRow = _hssfSheet.getRow(DBConstants.ROW_NUM_DB_FILED_NAME);
		HSSFCell hssCell = hssfRow.getCell(DBConstants.COL_NUM_VALUE_START);
		
		//excel中要求导出的字段
		Vector<ExcelField> fields = new Vector<ExcelField>();
		String columnName = "";
		int i = DBConstants.COL_NUM_VALUE_START;
		do{
			hssCell = hssfRow.getCell(i);
			if(hssCell == null)
				break;
			
			columnName = ExcelUtil.getString(hssCell);
			boolean flag = _rs.columnIsExist(columnName);
			if(columnName != null && !columnName.equals("") && flag){
				ExcelField field = new ExcelField();
				field.setCellNum(i);
				field.setName(columnName);
				
				fields.addElement(field);
			}
			i++;
		}while(true);
		
		return fields;
	}
	//设置数据
	private void setDatas2(Vector<ExcelField> fields) throws Exception{	
		HSSFRow hssfRow = null;
		HSSFCell hssCell = null;
		String val = "";
		for(int i=0; i<_rs.getRowCount(); i++){		
			hssfRow = _hssfSheet.createRow(DBConstants.ROW_NUM_DATA_START+i);
			for(int j=0; j<fields.size(); j++){
				hssCell = hssfRow.createCell(fields.get(j).getCellNum());
				val =_rs.getString(i, j);
				hssCell.setCellValue(val);
			}
		}
	}
	//按excel定义的列导出
	public boolean export2(String path, String templatePaht, MyJDBC db, String sql) throws Exception{
		try {
			init(templatePaht, db, sql);
			Vector<ExcelField> fields = getFieldsFromExcel();
			setDatas2(fields);			
			save(path);
		} catch (Exception e) {
			throw new MyException(e, "ExportToExcel::export2-->path=", path, 
					"\r\n templatePaht=", templatePaht,
					"\r\n dbs=", db.getDBS(),
					"\r\n dataSource=", db.getDataSource().toString(),
					"\r\n sql=", sql
					);
		} finally {
			closeExcel();
		}	
		return true;
	}
	public boolean export2(OutputStream out, String templatePaht, MyJDBC db, String sql) throws Exception{
		_saveOut = out;
		return export2("", templatePaht, db, sql);
	}
		
	//导出表
	public boolean exportTable2(String path, String templatePaht, MyJDBC db, String tableName) throws Exception{
		String sql = "select * from " + tableName;
		return export2(path, templatePaht, db, sql);
	}
}
