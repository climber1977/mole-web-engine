package com.mole.webengine.dbapp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Types;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.file.ExcelUtil;

public class ImportToDB {	
	//测试标示
	private boolean testFlag = false;
	
	private String tableName = "";	
	
	//id
	private String idFieldName = "";
	private String generateIdSql = "";
		
	//导入时唯一标示
	private String itemIdFiledName = "";
	private int itemIdFiledIndex = 0;
	
	//字段名
	private Vector<ExcelField> fields;	
	
	//字段状态字段
	private final String itemStateFiledName = "item_state";
	private int recStateFiledIndex = 0;
	
	//执行的sql
	private String insertSql = "";  
	private String updateSql = "";  
	private String whereSql = "";
	
	//表结果描述
	MyResultSetMetaData _resultSetMetaData = null;
	
	MyJDBC _db = null;
	private Long maxId = 0L;
	
	//取得下一个ID
	private Long getNextMaxId(){
		return ++this.maxId;
	}
	//ID的取得方式是否是，取最大值
	boolean isGenerateID(){
		if(!generateIdSql.equals("")){
			return true;
		}
		return false;
	}
	//从excel中读取表名
	private boolean readTableNameFromExcel(HSSFRow hssfRow) throws Exception{
		HSSFCell hssCell = hssfRow.getCell(DBConstants.COL_NUM_VALUE_START);
		if(hssCell == null){
			return false;
		}
		this.tableName = ExcelUtil.getString(hssCell);
		_resultSetMetaData = _db.getTableColumns(tableName);
		return getTableDesc(tableName);		
	}
	
	//读取字段名
	private boolean readFiledName(HSSFRow hssfRow){
		int i = DBConstants.COL_NUM_VALUE_START;
		String columnName = "";
		HSSFCell hssCell = null;
		boolean result = false;
		fields = new Vector<ExcelField>();
		
		do{
			hssCell = hssfRow.getCell(i);
			if(hssCell == null)
				break;
			
			columnName = ExcelUtil.getString(hssCell);
			if(columnName != null && !columnName.equals("")){
				ExcelField field = new ExcelField();
				field.setName(columnName);
				field.setCellNum(i);
				
				//唯一标示id字段
				if((itemIdFiledName.equals("") && fields.size() == 0) ||
						field.equalsIgnoreCase(this.itemIdFiledName)	){
					this.itemIdFiledIndex = fields.size();
				}
				
				//记录状态字段
				if(field.equalsIgnoreCase(this.itemStateFiledName)){
					this.recStateFiledIndex = fields.size();
				}
				
				fields.addElement(field);
				
				result = true;
			}
			i++;
		}while(true);
		return result;
	}
	
	//读取表头
	private boolean readHead(HSSFRow hssfRow) throws Exception{
		if(hssfRow == null){
			return false;
		}
			
		switch(hssfRow.getRowNum()){
		case DBConstants.ROW_NUM_TABLENAME:
			return readTableNameFromExcel(hssfRow);	
		case DBConstants.ROW_NUM_DB_FILED_NAME:
			return readFiledName(hssfRow);
		}
		return true;
	}
	//组装插入sql
	private String assembleInsertSql(Vector<Object> datas) throws Exception{
		//this.insertSql += " values (v1, v2, v3,...)";
		String sql = this.insertSql + " values (";
		
		String fieldsSql = "";
		if(isGenerateID()){
			fieldsSql += this.getNextMaxId();
		}
		for(int i=0; i<datas.size(); i++){
			if(!fieldsSql.equals("")){
				fieldsSql += ",";
			}
			
			String fieldName = this.fields.get(i).getName();
			
			if(_resultSetMetaData.columnIsString(fieldName)){
				fieldsSql += "'" + datas.get(i).toString() + "'";				
			}
			else{
				fieldsSql += datas.get(i).toString();
			}
		}
		sql += fieldsSql + ")";		
		//System.out.println("sql=[" + sql + "]");
		return sql;
	}
	//组装where条件
	private String assembleWhereSql(Vector<Object> datas) throws Exception{
		String sql = this.whereSql;
		String val = datas.get(this.itemIdFiledIndex).toString();
		String fieldName = this.fields.get(this.itemIdFiledIndex).getName();
		
		if(_resultSetMetaData.columnIsString(fieldName)){
			sql += "'" + val + "'";
		}
		else{
			sql += val;
		}
		return sql;
	}
	//组装update sql
	private String assembleUpdateSql(Vector<Object> datas) throws Exception{
		//this.updateSql += " f1=v1, f2=v2 ... where itemid=";
		String sql = this.updateSql + " ";
		String val = "";
		
		String fieldsSql = "";		
		for(int i=0; i<datas.size(); i++){
			if(!fieldsSql.equals("")){
				fieldsSql += ",";
			}
			val = datas.get(i).toString();
			String fieldName = this.fields.get(i).getName();
			
			fieldsSql += fieldName + "=";
			if(_resultSetMetaData.columnIsString(fieldName)){
				fieldsSql += "'" + val + "'";
			}
			else{
				fieldsSql += val;
			}
		}
		sql += fieldsSql;
		sql += assembleWhereSql(datas);
		//System.out.println("sql=[" + sql + "]");
		return sql;
	}
	//组装update sql
	private String assembleSelectSql(Vector<Object> datas) throws Exception{
		String sql = " select count(1)  as CNT from " + this.tableName + " ";
		sql += assembleWhereSql(datas);
		//System.out.println("sql=[" + sql + "]");
		return sql;
	}
	//组装删除语句
	private String assembleDeleteSql(Vector<Object> datas) throws Exception{
		//this.updateSql += " f1=v1, f2=v2 ... where itemid=";
		String sql = this.updateSql + " ";
		String val = "";
		
		sql += this.itemStateFiledName + "=0 ";
		sql += this.whereSql;
		val = datas.get(this.itemIdFiledIndex).toString();
		
		String fieldName = this.fields.get(this.itemIdFiledIndex).getName();
		if(_resultSetMetaData.columnIsString(fieldName)){	
			sql += "'" + val + "'";
		}
		else{
			sql += val;
		}
		//System.out.println("sql=[" + sql + "]");
		return sql;
	}
	//检查记录是否已存在
	private boolean isExistRec(Vector<Object> datas) throws Exception{
		String sql = assembleSelectSql(datas);
		long result = _db.getR0C0Long(sql);
		if(result > 0) {
			return true;
		}		
		
		return false;
	}
	//插入数据
	private boolean insertData(Vector<Object> datas) throws Exception{
		String sql = assembleInsertSql(datas);				
		_db.executeUpdate(sql);
		return true;
	}
	//修改数据
	private boolean updateData(Vector<Object> datas) throws Exception{
		String sql = assembleUpdateSql(datas);	
		_db.executeUpdate(sql);
		return true;
	}
	//删除数据
	private boolean deleteData(Vector<Object> datas) throws Exception{
		String sql = assembleDeleteSql(datas);			
		_db.executeUpdate(sql);
		return true;
	}
	//更新数据库
	private boolean updateDatabase(Vector<Object> datas) throws Exception{
		boolean ret = false;
		Integer recState = (Integer)datas.get(this.recStateFiledIndex);
		switch(recState)
		{
		case 0:
            ret = deleteData(datas);
			break;
		case 1:			
			if(isExistRec(datas)){
				ret = updateData(datas);
			}
			else{
				ret = insertData(datas);
			}
			break;
		case 2:
            ret = insertData(datas);
			break;
		}
		return ret;
	}
	//读取excel
	private boolean readData(HSSFRow hssfRow) throws Exception{
		ExcelField field = null;
		HSSFCell hssCell = null;
		Object obj = null;
		Vector<Object> datas = new Vector<Object>();
		for(int i=0; i<this.fields.size(); i++){
			field = this.fields.get(i);
			hssCell = hssfRow.getCell(field.getCellNum());
					
			switch(_resultSetMetaData.getColumnType(field.getName())){
			case Types.VARCHAR:
				obj = ExcelUtil.getString(hssCell);
				break;
			case Types.INTEGER:
			case Types.BIGINT:
				obj = ExcelUtil.getInteger(hssCell);
				break;
			case Types.DOUBLE:
			case Types.FLOAT:
				obj = ExcelUtil.getDouble(hssCell);
				break;
			}
			datas.addElement(obj);
		}
		return updateDatabase(datas);
	}
	//插入数据
	private boolean initInsertSql() {
		//"insert into tablename(f1, f2, f3,...)"
		this.insertSql = "insert into ";
		this.insertSql += this.tableName + " (";
		
		String fieldsSql = "";
		if(isGenerateID()){
			fieldsSql += this.idFieldName;
		}
		
		for(int i=0; i<this.fields.size(); i++){
			if(!fieldsSql.equals(""))
			{
				fieldsSql += ",";
			}
			fieldsSql += this.fields.get(i).getName();
		}
		this.insertSql +=  fieldsSql + ")";
		return true;
	}
	//初始化update sql
	private boolean initUpdateSql() {
		this.updateSql = "update ";
		this.updateSql += this.tableName + " set ";
		this.whereSql = " where " + this.itemIdFiledName + "=";
				
		return true;
	}
	//读取excel
	public boolean readSheet(HSSFSheet hssfSheet) throws Exception{		
		// 循环行Row		
		for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}
			if(rowNum < DBConstants.ROW_NUM_DATA_START){
				if(!readHead(hssfRow)){
					return false;
				}
				continue;
			}
			if(rowNum == DBConstants.ROW_NUM_DATA_START){
				if(!initInsertSql()){
					return false;
				}
				if(!initUpdateSql()){
					return false;
				}
				if(!initUpdateSql()){
					return false;
				}
			}
			readData(hssfRow);
		}
		return true;
	}
	//读取excel
	public boolean importToDB(String path, MyJDBC db) throws Exception{
		FileInputStream fStream = new FileInputStream(path);
		return importToDB(fStream, db);
	}
	//读取excel
	public boolean importToDB(InputStream in, MyJDBC db) throws Exception{
		_db = db;	
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		// 循环工作表Sheet
		for (int sheetNum = 0; sheetNum < hssfWorkbook.getNumberOfSheets(); sheetNum++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetNum);
			if (hssfSheet == null) {
				continue;
			}
			if(!readSheet(hssfSheet)){
				return false;
			}
		}
		return true;
	}

	public boolean isTestFlag() {
		return testFlag;
	}

	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}
	
	//取得表描述，没有时，id不需要插入，第一列就是唯一键值
	public boolean getTableDesc(String tableName) throws Exception {
		ImportConfigInfo info = ImportConfigManager.getDatableByUUID(tableName);		
		if(info == null){
			throw new MyException("can't find " + tableName + " in wb_import_config.");
		}
		idFieldName = info.getIdField();
		itemIdFiledName = info.getUniqueField();
		generateIdSql = info.getGenerateIdSql();
		if(generateIdSql!= null & !generateIdSql.equals("")){
			this.maxId = _db.getR0C0Long(generateIdSql);
		}
		return true;
	}
}
