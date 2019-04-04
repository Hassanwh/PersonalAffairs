package code.person.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;



/**
 * 
 * 目的:FTP文件类 <br/>
 * 
 * FTP上传文件、下载文件
 * 
 * @author zhangchanghai
 * 
 *         *
 */
public class FTPUtil{
	Logger logger = Logger.getLogger(this.getClass());
	private FTPClient ftpClient;

	private boolean connFlag = false;

	private static int BUFFER_SIZE = 100000;

	public FTPUtil(String url, int port, String username, String password) {
		this.ftpClient = new FTPClient();
		try {
			ftpClient.connect(url, port);
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("utf-8");
			ftpClient.setBufferSize(BUFFER_SIZE);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			connFlag = true;
		} catch (Exception e) {
			logger.error("构造FTPUtil失败", e);
		} finally {
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.error("FTP连接失败");
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException e) {
					logger.error("关闭FTP失败", e);
				}
			}
		}
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url 		FTP服务器hostname
	 * @param port		FTP服务器端口
	 * @param username	FTP登录账号
	 * @param password	FTP登录密码
	 * @param path		FTP服务器保存目录,如果是根目录则为“/”
	 * @param filename	上传到FTP服务器上的文件名
	 * @param input		本地文件输入流
	 * @return 			成功返回true，否则返回false
	 */
	public boolean uploadFile(String path, String filename, InputStream input, String type) {
		//log.info("uploadFile..." + filename);
		boolean result = false;
		try {
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (connFlag) {
				boolean change = ftpClient.changeWorkingDirectory(path);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				//log.info("uploadFile..." + change);
				change = true;
				if (change) {
					if (StringUtils.equals("cover", type)) {
						uploadCore4Cover(filename, input);
					} else if (StringUtils.equals("tmp", type)) {
						result = uploadCore4Tmp(filename, input);
					} else {
						throw new Exception();
					}
				}
			} else {
				System.out.println("调用uploadFile方法，FTP连接失败");
			}
			input.close();
			ftpClient.logout();
		} catch (IOException e) {
			System.out.println("FTP上传文件异常" + e);
		} catch (Exception e) {
			System.out.println("异常" + e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException ioe) {
					//log.error("FTP关闭链接失败" + ioe);
				}
			}
		}
		return result;
	}

	private boolean uploadCore4Cover(String filename, InputStream input) {
		boolean flag = false;
		try {
			ftpClient.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1"), input);
			logger.info("uploadCore1上传成功");
			flag = true;
		} catch (UnsupportedEncodingException e) {
			System.out.println("uploadCore4Cover()字符编码异常" + e);
		} catch (IOException e) {
			System.out.println("uploadCore4Cover()读写异常" + e);
		}
		return flag;
	}

	private boolean uploadCore4Tmp(String filename, InputStream input) {
		boolean result = false;
		try {
			String fileTempName = filename.substring(0, filename.lastIndexOf(".")) + ".tmp";
			if (findRemoteFile(fileTempName) != null && ftpClient.sendCommand("dele " + fileTempName + "\r\n") == 250) {
				//log.info("缓存文件[" + fileTempName + "]存在,执行删除");
			} else if (findRemoteFile(filename) != null && ftpClient.sendCommand("dele " + filename + "\r\n") == 250) {
				System.out.println("历史文件[" + filename + "]存在,执行删除");
			}
			if (ftpClient.storeFile(new String(fileTempName.getBytes("utf-8"), "iso-8859-1"), input)) {
				System.out.println("文件[" + fileTempName + "]上传成功!");
				ftpClient.rename(fileTempName, filename);
				System.out.println("文件[" + fileTempName + "]更名为[" + filename + "]成功!");
				result = true;
			} else {
				System.out.println("文件[" + fileTempName + "]上传失败!");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("uploadCore4Tmp字符编码异常" + e);
		} catch (IOException e) {
			System.out.println("uploadCore4Tmp读写异常" + e);
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param url			FTP服务器hostname
	 * @param port			FTP服务器端口
	 * @param username		FTP登录账号
	 * @param password		FTP登录密码
	 * @param remotePath	FTP服务器上的相对路径
	 * @param fileName		要下载的文件名
	 * @param localPath		下载后保存到本地的路径
	 * @return
	 */
	public boolean downFile(String remotePath, String fileName, String localPath) {
		boolean result = false;
		try {
			if (connFlag) {
				// 转移到FTP服务器目录至指定的目录下
				ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("utf-8"), "iso-8859-1"));
				// 获取文件列表
				FTPFile[] fs = ftpClient.listFiles();
				for (FTPFile ff : fs) {
					if (ff.getName().equals(fileName)) {
						File localFile = new File(localPath + "/" + ff.getName());
						OutputStream is = new FileOutputStream(localFile);
						ftpClient.retrieveFile(ff.getName(), is);
						is.close();
					}
				}
			} else {
				System.out.println("调用downFile方法，FTP连接失败");
			}
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			System.out.println("FTP上传文件异常" + e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException ioe) {
					System.out.println("FTP关闭链接失败" + ioe);
				}
			}
		}
		return result;
	}

	public FTPFile findRemoteFile(String url, int port, String username, String password, String path, String fileTempName) throws IOException {
		if (!connFlag) {
			ftpClient.connect(url, port);
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("utf-8");
			ftpClient.setBufferSize(BUFFER_SIZE);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			connFlag = true;
			if (ftpClient.changeWorkingDirectory(path)) {
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				return findRemoteFile(fileTempName);
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * 查找远程文件
	 * 
	 * @param fileTempName
	 * @return
	 * @throws IOException
	 */
	private FTPFile findRemoteFile(String fileTempName) throws IOException {

		FTPFile[] files = ftpClient.listFiles();
		for (FTPFile ftpFile : files) {
			if (StringUtils.equals(fileTempName, ftpFile.getName())) {
				return ftpFile;
			}
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException {
		// 文件上传
//		try {
//			FileInputStream in = new FileInputStream(new File("G:/Frank_apps/系统/zysong.ttf"));
//			FTPUtil f = new FTPUtil("192.168.0.123", 21, "frank", "123456");
//			f.uploadFile("/home/frank/1/", "zysong.ttf", in, "tmp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 文件下载
		try {
			FTPUtil f = new FTPUtil("200.1.1.183", 21, "bips", "pass");
			f.downFile("/home/bips/zch/", "aml_item_rel.unl", "C:/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
