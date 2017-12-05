package com.mole.test.webengine.utils;

import com.mole.webengine.file.FileUtils;

public class FileUtilsTest {
	public static void main(String args[]) throws Exception {
		String path = FileUtils.conjPaths("a", "b");
		System.out.println(path);
		
		path = FileUtils.conjPaths("a/", "b");
		System.out.println(path);
		
		path = FileUtils.conjPaths("a/", "/b");
		System.out.println(path);
		
		path = FileUtils.conjPaths("a", "/b");
		System.out.println(path);
		
		path = FileUtils.conjPaths("a", "/b", "c", "/d", "e/", "/f", "g", "/h");
		System.out.println(path);

	}
}
