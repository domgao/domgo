package com.domgo.commons.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileUtil
 * @author Mr.Domgo
 * @Date 2020-9-18
 * @Since 1.0
 */
public class FileUtils extends org.apache.commons.io.FileUtils{

	public static final Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * create File
	 * @param fullPathfileName 文件全路径名
	 * @throws IOException 
	 */
	public static void deleteAndcreateFile(String fullPathfileName) throws IOException {
		Path path = Paths.get(fullPathfileName);
		boolean exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		if(exists) {
			Files.delete(path);
		}
		File file = new File(fullPathfileName);
		File parentFile = file.getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		file.createNewFile();
		log.info("创建文件File[{}]成功", fullPathfileName);
	}
	
	public static boolean isDirectory(String path) {
		return new File(path).isDirectory();
	}
	
	public boolean isFile(String path) {
		return new File(path).isFile();
	}
	
}
