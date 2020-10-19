package com.domgo.web.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * FileUtils
 * @author domgao
 * 2020年4月8日
 */
public class FileUtils {

	private static final Logger log = Logger.getLogger(FileUtils.class.toString());

	public static void deleteAndNewFile(String text, String dest) {
		Path path = Paths.get(dest);
		boolean exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		if(exists) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.severe("File" + dest + "Delete Fail! Because of" + e);
			}
			createFile(text, path);
		}else {
			createFile(text, path);
		}
	}

	public static void createFile(String text, Path src) {
		File file = src.toFile();
		File parentFile = file.getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			log.severe("Create File" + src + "Fail! Because of" + e);
		}
		try {
			Files.write(file.toPath(), text.getBytes());
		} catch (IOException e) {
			log.severe("File" + src + "Write Fail! Because of" + e);
		}
	}
}
