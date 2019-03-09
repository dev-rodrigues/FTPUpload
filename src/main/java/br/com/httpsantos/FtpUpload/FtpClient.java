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

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @constructor
	 */
	public FtpClient(String server, int port, String user, String password) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @returns true if connection succeeds
	 */
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

		return ftp.login(user, password);
	}

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @parameter path
	 * 
	 * @returns a list of files in the directory
	 */
	public List<String> listFiles(String path) throws IOException {
		List<String> arq = new ArrayList<String>();
		FTPFile[] files = ftp.listFiles(path);
		for (FTPFile ftpFile : files) {
			arq.add(ftpFile.getName());
		}
		return arq;
	}

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @parameter String source, String destination
	 * 
	 * @returns true if downloaded
	 */
	public boolean downloadFile(String source, String destination) throws IOException {
		FileOutputStream out = new FileOutputStream(destination);
		return ftp.retrieveFile(source, out);
	}

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @parameter String source, String destination
	 * 
	 * @returns true if uploaded
	 */
	public boolean uploadFile(File file, String path) throws IOException {
		return ftp.storeFile(path, new FileInputStream(file));
	}

	/*
	 * @author Carlos Henrique
	 * 
	 * @github github.com/httpsantos
	 * 
	 * @parameter:
	 * 
	 * @returns:
	 */
	public void close() throws IOException {
		ftp.disconnect();
	}
}