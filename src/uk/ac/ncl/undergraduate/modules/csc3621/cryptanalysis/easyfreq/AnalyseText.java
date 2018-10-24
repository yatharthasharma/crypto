package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
	public static void freqAnalysisOfPg1661(String path){
		
	}
	// check if given character is within range 0-25 for character array that's being used in VigenereCipher class -> encrypt and decrypt methods
	public static boolean outOfLimits(char x){	
		return (x > 25 || x < 0);
	}
	public static double indexOfCoincidence(String cipherText, int key){
		cipherText = cipherText.toUpperCase();
		String[] arrayOfArrays = new String[cipherText.length()/key];
		for (int j = 0; j < arrayOfArrays.length; j++){
			for (int i = 0; i <= cipherText.length(); i++){
				arrayOfArrays[j] += cipherText.charAt(i);
				i += key;
			}
		}
		return 0d;
	}
	public static double averageIndexOfCoincidence(String[] x){
		
		return 0d;
	}
	public static int[] numberOfLetters (String cipherText, char x){
		Map<Character, Double> freq = new HashMap<Character, Double>();
		int totalCountOfLetters = 0;
		for (char xyz = 'A'; xyz <= 'Z'; xyz++) {
			freq.put(xyz, 0d);
		}
		for (int i = 0; i < cipherText.length(); i++){
			char ch = Character.toUpperCase(cipherText.charAt(i));
			if ((ch >= 65 && ch <= 90)) {
				freq.put(ch, freq.get(ch) + 1);
				totalCountOfLetters++;
			}
		}
		int[] val = {freq.get(x).intValue(), totalCountOfLetters};
		return val;
	}
}