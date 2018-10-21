package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptVigenere {
	public static void main(String[] args){
		try {
			String mainPath = Paths.get(EncryptVigenere.class.getResource("/").toURI()).toString();
			String plaintextFilePath = mainPath + "/res/pg1661.txt";
			byte[] bytes = Files.readAllBytes(Paths.get(plaintextFilePath)); // input sample text from the given file
			String str = new String(bytes, StandardCharsets.UTF_8);
			String encryptedText = VigenereCipher.encrypt(str, "hello");
			String decryptedText = VigenereCipher.decrypt(encryptedText, "hello");
			System.out.println();
			FrequencyAnalyser x = new FrequencyAnalyser();
			AnalyseText y = new AnalyseText();
			y.Analysing(plaintextFilePath, x);
			FrequencyTable z = x.analyse();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
