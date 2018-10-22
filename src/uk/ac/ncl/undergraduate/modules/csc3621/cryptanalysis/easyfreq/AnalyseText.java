package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnalyseText {
	public static void Analysing(String path, FrequencyAnalyser obj) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path)); // input sample text from the given file
			String str = new String(bytes, StandardCharsets.UTF_8);
			obj.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// freq of english letters from pg1661.txt for ex1part1 -> CREATE METHOD
	// check if given character is within range 0-25 for character array that's being used in VigenereCipher class
	public static boolean outOfLimits(char x){							
		if (x > 25 || x < 0){
			return true;
		}
		return false;
	}
}