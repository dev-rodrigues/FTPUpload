package br.com.httpsantos.FtpUpload;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class App {
	public static void main(String[] args) throws SocketException, IOException {

		FTPClient ftp = new FTPClient();

		ftp.connect(""); // ftp.petrobras.com.br

		ftp.login("usuario", "senha");

		ftp.changeWorkingDirectory("diretorio");

		String[] arq = ftp.listNames();

		System.out.println("Listando arquivos: \n");

		for (String f : arq) {
			System.out.println(f);
		}
	}
}