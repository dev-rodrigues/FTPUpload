package br.com.httpsantos.FtpUpload;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class App {
	public static void main(String[] args) throws SocketException, IOException {
		
		FTPClient ftp = new FTPClient();
		
		ftp.connect("192.168.0.18");

		ftp.login("user", "123");

		FileInputStream arqEnviar = new FileInputStream("C:\\temp\\out.txt");

		if (ftp.storeFile("meuarquivo.txt", arqEnviar)) {
			System.out.println("Arquivo armazenado com sucesso!");
		} else {
			System.out.println("Erro ao armazenar o arquivo.");
		}
	}
}