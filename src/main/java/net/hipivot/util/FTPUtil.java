package net.hipivot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import net.hipivot.data.DownloadStatus;
import net.hipivot.data.UploadStatus;

public class FTPUtil {

	FTPClient ftpClient = new FTPClient();
	private String host;
	private int port;
	private String username;
	private String password;

	public FTPUtil(String host, int port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/**
	 * 获取指定目录下的文件总数
	 * @param directory
	 * @return
	 */
	public Integer totalDir(String directory){
		Integer totalCount = null;
		try {
			ftpClient.changeWorkingDirectory(directory);
			FTPFile[] files=ftpClient.listDirectories();
			totalCount=files.length;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件目录不存在！");
		}
		return totalCount;
	}
	
	/**
	 * 获取指定目录下的文件夹名
	 * @param directory
	 * @return
	 */
	public List<String> listDir(String directory){
		List<String> nameList=new ArrayList<String>();
		try {
			ftpClient.changeWorkingDirectory(directory);
			FTPFile[] files=ftpClient.listDirectories();
			for(FTPFile file:files){
				String fileName=new String(file.getName().getBytes("iso8859-1"),"utf-8");
				nameList.add(fileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件目录不存在！");
		}
		return nameList;
	}

	// 获取目录下的所有文件
	public void ListFile(String directory) throws Exception {
		ftpClient.changeWorkingDirectory(directory);
		FTPFile[] files = ftpClient.listFiles();
		for (FTPFile file : files) {
			if (file.isFile()) {
				String fileName=new String(file.getName().getBytes("iso8859-1"),"utf-8");
				System.out.println(fileName);
			}
		}
	}

	// 下载文件
	public DownloadStatus downloadFile(String remote, String loacl) throws Exception {
		DownloadStatus result = DownloadStatus.DOWNLOAD_FAILED;
		FTPFile[] files = null;
		try {
			files = ftpClient.listFiles(new String((remote).getBytes("utf-8"), "iso8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (files.length != 1) {
			System.out.println("！！远程文件不存在！！");
			return DownloadStatus.REMOT_FILE_NOTEXIST;
		}
		File file = new File(loacl);
		OutputStream outputStream = null;
		InputStream inputStream = null;
		byte[] bytes = new byte[1024];
		long remotesize = files[0].getSize();
		long step = remotesize / 100;
		long localsize = 0L;
		long process = 0;
		int c;
		if (file.exists()) {
			localsize = file.length();
			if (localsize >= remotesize) {
				System.out.println("！！本地文件已存在！！");
				return DownloadStatus.LOCAL_BIGGER_REMOTE;
			} else {
				System.out.println("====断点续传====");
				outputStream = new FileOutputStream(file, true);
				ftpClient.setRestartOffset(localsize);
				step = remotesize / 100;
				process = localsize / step;
			}
		} else {
			System.out.println("====正常下载====");
			outputStream = new FileOutputStream(file);
		}
		try {
			inputStream = ftpClient.retrieveFileStream(new String((remote).getBytes("utf-8"), "iso8859-1"));
			while ((c = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, c);
				localsize += c;
				long nowprocess = localsize / step;
				if (nowprocess > process) {
					process = nowprocess;
					if (process % 10 == 0) {
						System.out.println(remote + ":" + process + "%");
					}
				}
			}
			inputStream.close();
			outputStream.close();
			boolean updatestatus = ftpClient.completePendingCommand();
			if (updatestatus && process == 100) {
				result = DownloadStatus.DOWNLOAD_SUCCESS;
			} else {
				result = DownloadStatus.DOWNLOAD_FAILED;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		return result;
	}

	// 上传文件
	public UploadStatus uploadFile(String remote, String loacl) throws Exception {
		FTPFile[] files = ftpClient.listFiles(new String(("/var/ftp/pub" + remote).getBytes("UTF-8"), "IOS8859-1"));
		if (files.length == 1) {
			System.out.println("远程文件已存在");
			System.out.println("断点续传");

		} else {
			System.out.println("远程文件不存在");
			System.out.println("新文件上传");

		}
		File file = new File(loacl);
		File[] files2 = file.listFiles();
		InputStream inputStream;
		OutputStream outputStream;
		byte[] bytes = new byte[1024];
		int c;
		inputStream = new FileInputStream(loacl);
		outputStream = ftpClient.storeFileStream(remote);
		while ((c = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, c);
		}
		inputStream.close();
		outputStream.close();
		return null;
	}

	// 连接FTP
	public void connect() throws Exception {
		int count = 0;
		while (count < 10) {
			if (ftpClient.isConnected()) {
				System.out.println("！！！服务器已连接！！！");
				break;
			}
			ftpClient.connect(host, port);
			ftpClient.setBufferSize(1024);
			//ftpClient.enterLocalPassiveMode();
			ftpClient.setDefaultTimeout(60 * 1000);
			ftpClient.setConnectTimeout(60 * 1000);
			ftpClient.setDataTimeout(60 * 1000);
			ftpClient.login(username, password);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			count++;
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("！！！重连异常失败！！！");
			}
		}
	}

	// 关闭连接
	public void disConnect() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				System.out.println("！！！FTP连接断开！！！");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("！！！FTP异常断开！！！");
			}
		}
	}

	public static void main(String[] args) {
		FTPUtil ftpUtil = new FTPUtil("54.219.185.173", 21, "anonymous", "982190423@qq.com");
		try {
			ftpUtil.connect();
			//总数
			System.out.println(ftpUtil.totalDir("games"));
			//名称
			for(String name:ftpUtil.listDir("games")){
				System.out.println(name);
			}
			ftpUtil.disConnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftpUtil.disConnect();
		}
	}

}
