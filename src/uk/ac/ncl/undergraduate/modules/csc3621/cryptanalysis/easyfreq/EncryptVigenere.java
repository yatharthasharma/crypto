package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptVigenere {
	public static void main(String[] args) {
		try {
			String mainPath = Paths.get(EncryptVigenere.class.getResource("/").toURI()).toString();
			String plaintextFilePath = mainPath + "/res/pg1661.txt";
			String encPath = mainPath + "/mine/encPath.txt";
			String decPath = mainPath + "/mine/decPath.txt";
			String freqAnal = mainPath + "/mine/freqAnal.txt";
			byte[] bytes = Files.readAllBytes(Paths.get(plaintextFilePath));
			String str = new String(bytes, StandardCharsets.UTF_8);
			System.out.println("entereing encrypt ");
			String encryptedText = VigenereCipher.encrypt(str, "nclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjfnclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjfnclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjf");
			System.out.println("entereing decrypt ");
			String decryptedText = VigenereCipher.decrypt(encryptedText, "nclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjfnclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjfnclajbsdkjabskjbaskdbasukdbuaksbkfjdsbkjdbskjdbskdskbfkjdsbkjdbskjfdskjbfskjdbfkjsdbkjdbskjf");
			System.out.println("leaving decrypt ");
			Util.printBufferToFile(encryptedText, encPath);
			Util.printBufferToFile(decryptedText, decPath);
			FrequencyAnalyser x = new FrequencyAnalyser();
			AnalyseText.Analysing(encPath, x);
			FrequencyTable z = x.analyse();
			for (int i = 0; i < z.getTable().length; i++){
				System.out.println(z.getTable()[i]);
				Util.printBufferToFile(Double.toString(z.getTable()[i]), freqAnal);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
