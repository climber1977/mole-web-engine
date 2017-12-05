package com.mole.webengine.file;

import java.io.File;

import com.mole.webengine.debug.MyException;
import com.mole.webengine.file.FileUtils;
import com.mole.webengine.system.SysParams;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilesCache {
	private static final Logger logger = LoggerFactory.getLogger(FilesCache.class);
	private static HashMap<String, String> files = new HashMap<String, String>();
	
	public static boolean loadFiles(String dir) throws Exception {		
		File f = new File(dir);
		String[] names = f.list(null);
		String path = "";
		for (int i = 0; i < names.length; i++) {
			path = FileUtils.conjPaths(dir, names[i]);
			if (FileUtils.isDirectory(path)) {
				loadFiles(path);
			} else	if (!loadFile(path)) {
				logger.error("LoadJS [" + path + "] failed.");
			}
		}
		return true;
	}

	private static boolean loadFile(String filePath) throws Exception {
		String content = FileUtils.readToStringDefaultChartSet(filePath);
		synchronized (files) {
			files.put(filePath, content);
		}
		return true;
	}

	public static String getContent(String filePath) throws Exception {
		if(!SysParams.isUseSysCache()){
			return FileUtils.readToStringDefaultChartSet(filePath);
		}
		if(!files.containsKey(filePath)){
			throw new MyException("FilesCache::getContent-->cann't read [", filePath, "]");
		}
		return files.get(filePath);
	}
	
	public static String getTemplate(String fileName) throws Exception {
		String path = SysParams.getTemplateFilePath(fileName);
		return getContent(path);
	}
	public static String getJson(String fileName) throws Exception {
		String path = SysParams.getJsonPath(fileName);
		return getContent(path);
	}
}
