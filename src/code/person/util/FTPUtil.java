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
 * Ŀ��:FTP�ļ��� <br/>
 * 
 * FTP�ϴ��ļ��������ļ�
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
			logger.error("����FTPUtilʧ��", e);
		} finally {
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.error("FTP����ʧ��");
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException e) {
					logger.error("�ر�FTPʧ��", e);
				}
			}
		}
	}

	/**
	 * Description: ��FTP�������ϴ��ļ�
	 * 
	 * @param url 		FTP������hostname
	 * @param port		FTP�������˿�
	 * @param username	FTP��¼�˺�
	 * @param password	FTP��¼����
	 * @param path		FTP����������Ŀ¼,����Ǹ�Ŀ¼��Ϊ��/��
	 * @param filename	�ϴ���FTP�������ϵ��ļ���
	 * @param input		�����ļ�������
	 * @return 			�ɹ�����true�����򷵻�false
	 */
	public boolean uploadFile(String path, String filename, InputStream input, String type) {
		//log.info("uploadFile..." + filename);
		boolean result = false;
		try {
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
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
				System.out.println("����uploadFile������FTP����ʧ��");
			}
			input.close();
			ftpClient.logout();
		} catch (IOException e) {
			System.out.println("FTP�ϴ��ļ��쳣" + e);
		} catch (Exception e) {
			System.out.println("�쳣" + e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException ioe) {
					//log.error("FTP�ر�����ʧ��" + ioe);
				}
			}
		}
		return result;
	}

	private boolean uploadCore4Cover(String filename, InputStream input) {
		boolean flag = false;
		try {
			ftpClient.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1"), input);
			logger.info("uploadCore1�ϴ��ɹ�");
			flag = true;
		} catch (UnsupportedEncodingException e) {
			System.out.println("uploadCore4Cover()�ַ������쳣" + e);
		} catch (IOException e) {
			System.out.println("uploadCore4Cover()��д�쳣" + e);
		}
		return flag;
	}

	private boolean uploadCore4Tmp(String filename, InputStream input) {
		boolean result = false;
		try {
			String fileTempName = filename.substring(0, filename.lastIndexOf(".")) + ".tmp";
			if (findRemoteFile(fileTempName) != null && ftpClient.sendCommand("dele " + fileTempName + "\r\n") == 250) {
				//log.info("�����ļ�[" + fileTempName + "]����,ִ��ɾ��");
			} else if (findRemoteFile(filename) != null && ftpClient.sendCommand("dele " + filename + "\r\n") == 250) {
				System.out.println("��ʷ�ļ�[" + filename + "]����,ִ��ɾ��");
			}
			if (ftpClient.storeFile(new String(fileTempName.getBytes("utf-8"), "iso-8859-1"), input)) {
				System.out.println("�ļ�[" + fileTempName + "]�ϴ��ɹ�!");
				ftpClient.rename(fileTempName, filename);
				System.out.println("�ļ�[" + fileTempName + "]����Ϊ[" + filename + "]�ɹ�!");
				result = true;
			} else {
				System.out.println("�ļ�[" + fileTempName + "]�ϴ�ʧ��!");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("uploadCore4Tmp�ַ������쳣" + e);
		} catch (IOException e) {
			System.out.println("uploadCore4Tmp��д�쳣" + e);
		}
		return result;
	}

	/**
	 * Description: ��FTP�����������ļ�
	 * 
	 * @param url			FTP������hostname
	 * @param port			FTP�������˿�
	 * @param username		FTP��¼�˺�
	 * @param password		FTP��¼����
	 * @param remotePath	FTP�������ϵ����·��
	 * @param fileName		Ҫ���ص��ļ���
	 * @param localPath		���غ󱣴浽���ص�·��
	 * @return
	 */
	public boolean downFile(String remotePath, String fileName, String localPath) {
		boolean result = false;
		try {
			if (connFlag) {
				// ת�Ƶ�FTP������Ŀ¼��ָ����Ŀ¼��
				ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("utf-8"), "iso-8859-1"));
				// ��ȡ�ļ��б�
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
				System.out.println("����downFile������FTP����ʧ��");
			}
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			System.out.println("FTP�ϴ��ļ��쳣" + e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					connFlag = false;
				} catch (IOException ioe) {
					System.out.println("FTP�ر�����ʧ��" + ioe);
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
	 * ����Զ���ļ�
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
		// �ļ��ϴ�
//		try {
//			FileInputStream in = new FileInputStream(new File("G:/Frank_apps/ϵͳ/zysong.ttf"));
//			FTPUtil f = new FTPUtil("192.168.0.123", 21, "frank", "123456");
//			f.uploadFile("/home/frank/1/", "zysong.ttf", in, "tmp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// �ļ�����
		try {
			FTPUtil f = new FTPUtil("200.1.1.183", 21, "bips", "pass");
			f.downFile("/home/bips/zch/", "aml_item_rel.unl", "C:/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
