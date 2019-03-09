package br.com.httpsantos.FtpUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/*
 * @author Carlos Henrique
 * @github github.com/httpsantos
 */
public class FtpClient {
	private String server;
	private int port;
	private String user;
	private String password;
	private FTPClient ftp;

	public FtpClient(String server, int port, String user, String password) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public boolean open() throws IOException {
		boolean connect = false;
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		ftp.connect(server, port);
		int reply = ftp.getReplyCode();

		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return connect;
		}

		if (ftp.login(user, password)) {
			connect = true;
		}
		return connect;
	}

	public List<String> listFiles(String path) throws IOException {
		List<String> arq = new ArrayList<String>();
		FTPFile[] files = ftp.listFiles(path);
		for (FTPFile ftpFile : files) {
			arq.add(ftpFile.getName());
		}
		return arq;
	}

	public void downloadFile(String source, String destination) throws IOException {
		FileOutputStream out = new FileOutputStream(destination);
		ftp.retrieveFile(source, out);
	}

	public void uploadFile(File file, String path) throws IOException {
		ftp.storeFile(path, new FileInputStream(file));
	}

	public void close() throws IOException {
		ftp.disconnect();
	}
}