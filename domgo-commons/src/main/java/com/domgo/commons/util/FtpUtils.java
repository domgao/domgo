package com.domgo.commons.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ftp工具
 * @author Mr.Domgo
 * @Date 2020-9-17
 * @Since 1.0
 */
public class FtpUtils {

	private static final Logger log = LoggerFactory.getLogger(FtpUtils.class);
	private static final String ROOT_DIRECTORY = "/";
	private static String LOCAL_CHARSET = "GBK";
	private static String SERVER_CHARSET = "ISO-8859-1";
	private static ThreadLocal<FTPClient> threadLocalFtpClient = new ThreadLocal<FTPClient>();
	
	private void createDirectory(FTPClient ftpClient, String dirPath) throws IOException {
		if(StringUtils.isNotBlank(dirPath)) {
			String[] dirs = dirPath.split("/");
			for (String dir : dirs) {
				if(StringUtils.isBlank(dir)) {
					continue;
				}
				if(!ftpClient.changeWorkingDirectory(dir)) {
					ftpClient.makeDirectory(dir);
					ftpClient.changeWorkingDirectory(dir);
				}
			}
		}
	}
	
	public static FTPClient getFTPClient() {
		FTPClient ftpClient = threadLocalFtpClient.get();
		if(ftpClient == null) {
			throw new RuntimeException("ftp连接已关闭");
		}
		return ftpClient;
	}
	
	public static boolean login(String hostname, int port, String username, String password) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding(LOCAL_CHARSET);
			ftpClient.connect(hostname, port);
		} catch (Exception e) {
			log.error("连接IP[" + hostname + "],PORT[" + port + "]服务器失败！", e);
		} 
		if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){   
            try {
				if(ftpClient.login(username, password)) {
					log.info("登录IP[{}],PORT[{}]服务器成功...", hostname, port);
					threadLocalFtpClient.set(ftpClient);
					return true;
				}
			} catch (IOException e) {
				log.error("连接IP[" + hostname + "],PORT[" + port + "]服务器失败,用户名或密码错误", e);
			}
        }
		ftpClient.disconnect();
		return false; 
	}
	
	public static void logout() throws Exception {
		FTPClient ftpClient = getFTPClient();
		if(ftpClient == null) {
			return;
		}
		if(ftpClient.isConnected()) {
			ftpClient.disconnect();
			threadLocalFtpClient.remove();
		}
	}
	
	public boolean download(String remotePath, String remoteFileName, String localPath, String localFileName) throws IOException {
		boolean returnFlag = false;
		FTPClient ftpClient = getFTPClient();
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.changeWorkingDirectory(ROOT_DIRECTORY);
		String localFullPathFileName = localPath + File.separatorChar + localFileName;
		String remoteFullPathFileName = remotePath + File.separatorChar +  remoteFileName;
		FileUtils.deleteAndcreateFile(localFullPathFileName);
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localFullPathFileName))) {
			ftpClient.changeWorkingDirectory(remotePath);
			log.info("远程文件File[{}]开始下载", remoteFullPathFileName);
			boolean downloadSuccess = ftpClient.retrieveFile(remoteFileName, bos);
			if(downloadSuccess) {
				log.info("远程文件SourceFile[{}]下载到TargetFile[{}]成功", remoteFullPathFileName, localFullPathFileName);
				returnFlag = true;
			}else {
				log.info("远程文件SourceFile[{}]下载到TargetFile[{}]失败", remoteFullPathFileName, localFullPathFileName);
			}
			return returnFlag;
		} catch (Exception e) {
			log.error("文件下载失败", e);
		}
		return returnFlag;
	}
	
	public boolean upload(String localPath, String localFileName, String remotePath, String remoteFileName) throws IOException {
		FTPClient ftpClient = getFTPClient();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		createDirectory(ftpClient, remotePath);
		String localFullPathFileName = localPath + File.separatorChar + localFileName;
		String remoteFullPathFileName = remotePath + File.separatorChar + remoteFileName;
		boolean retValue = ftpClient.storeFile(new String(remoteFileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET), new FileInputStream(new File(localFullPathFileName)));
		if (retValue) {
			log.info("本地文件SourceFile[{}]上传至TargetFile[{}]成功", localFullPathFileName, remoteFullPathFileName);
		} else {
			log.info("本地文件SourceFile[{}]上传至TargetFile[{}]失败", localFullPathFileName, remoteFullPathFileName);
		}
		return retValue;
	} 
	
}
