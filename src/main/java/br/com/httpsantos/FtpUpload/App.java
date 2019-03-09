package br.com.httpsantos.FtpUpload;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

public class App {
	public static void main(String[] args) throws SocketException, IOException {
		FtpClient ftp = new FtpClient("192.168.0.18", 21, "user", "123");
		if (ftp.open()) {
			System.out.println("Connected!");

			List<String> files = ftp.listFiles("\\");

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("Listing Files");
			for (String file : files) {
				System.out.println(file);
			}

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("Download Files");
			if (ftp.downloadFile("\\cheque.pdf", "C:\\Users\\rodri\\Downloads\\out.pdf")) {
				System.out.println("download successfully completed");
			}

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("Upload Files");
			File f = new File("C:\\temp\\out.txt");

			if (ftp.uploadFile(f, "\\" + f.getName() + "1")) {
				System.out.println("upload successfully completed");
			}
		}
		ftp.close();
		System.out.println("Disconnected!");
	}
}