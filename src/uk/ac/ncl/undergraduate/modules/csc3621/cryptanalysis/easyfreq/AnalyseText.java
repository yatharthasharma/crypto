package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AnalyseText {
	public static void Analysing(String path, FrequencyAnalyser obj) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path)); // input sample text
															// from the given
															// file
			String str = new String(bytes, StandardCharsets.UTF_8);
			obj.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static char maxCharByFilePath(String relativePath) {
		double[] freqArray = new double[26]; // freq analysis from pg1661
		char[] charArray = new char[26]; // freq analysis from pg1661
		double maxFreq = 0;
		char maxChar = 0;
		FrequencyAnalyser w = new FrequencyAnalyser();
		// AnalyseText analyse = new AnalyseText();
		String mainPath;
		try {
			mainPath = Paths.get(AnalyseText.class.getResource("/").toURI()).toString();
			String plaintextFilePath = mainPath + relativePath;
			AnalyseText.Analysing(plaintextFilePath, w);
			FrequencyTable x = w.analyse();
			freqArray = x.getTable();
			char c = 'A';
			for (int local = 0; local < 26; local++) {
				charArray[local] = c;
				c++;
			}
			for (int local = 0; local < freqArray.length; local++) {
				if (freqArray[local] > maxFreq) {
					maxFreq = freqArray[local];
					maxChar = charArray[local];
				}
			}
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		return maxChar;
	}

	public static char maxCharByCipherText(String ciphertext) {
		double[] freqArray = new double[26]; // freq analysis from pg1661
		char[] charArray = new char[26]; // freq analysis from pg1661
		double maxFreq = 0;
		char maxChar = 0;
		FrequencyAnalyser w = new FrequencyAnalyser();
		//AnalyseText.Analysing(ciphertext, w);
		w.setText(ciphertext);
		FrequencyTable x = w.analyse();
		freqArray = x.getTable();
		char c = 'A';
		for (int local = 0; local < 26; local++) {
			charArray[local] = c;
			c++;
		}
		for (int local = 0; local < freqArray.length; local++) {
			if (freqArray[local] > maxFreq) {
				maxFreq = freqArray[local];
				maxChar = charArray[local];
			}
		}
		return maxChar;
	}

	// freq of english letters from Exercise2Ciphertext.txt for ex2part4 ->
	// CREATE METHOD
	public static int[] freqAnalysisForVigenere(String[] arrayOfSubstrings) {
		char maxCharInEnglishTexts = maxCharByFilePath("/res/pg1661.txt");
		/*FrequencyAnalyser[] freqAnalyser = new FrequencyAnalyser[arrayOfSubstrings.length];
		FrequencyTable[] freqTable = new FrequencyTable[arrayOfSubstrings.length];*/
		int[] keys = new int[arrayOfSubstrings.length];
		for (int i = 0; i < arrayOfSubstrings.length; i++) {
/*			freqAnalyser[i] = new FrequencyAnalyser();
			freqTable[i] = new FrequencyTable();
			AnalyseText.Analysing(arrayOfSubstrings[i], freqAnalyser[i]);
			FrequencyTable x = freqAnalyser[i].analyse();*/
			keys[i] = (int) maxCharByCipherText(arrayOfSubstrings[i]) - (int) maxCharInEnglishTexts;
		}
		return keys;
	}

	// check if given character is within range 0-25 for character array that's
	// being used in VigenereCipher class -> encrypt and decrypt methods
	public static boolean outOfLimits(char x) {
		return (x > 25 || x < 0);
	}

	public static String[] getSubstrings(String cipherText, int keyLength) {
		cipherText = cipherText.toUpperCase();
		String[] arrayOfSubstrings = new String[keyLength];
		for (int j = 0; j < arrayOfSubstrings.length; j++) {
			arrayOfSubstrings[j] = "";
		}
		for (int j = 0; j < arrayOfSubstrings.length; j++) {
			for (int i = j; i < cipherText.length(); i++) {
				arrayOfSubstrings[j] += cipherText.charAt(i);
				i += keyLength - 1;
			}
		}
		return arrayOfSubstrings;
	}

	public static double indexOfCoincidence(String cipherText, int keyLength) { // index
																				// of
																				// coincidence
																				// given
																				// a
																				// specific
																				// key
																				// length
		cipherText = cipherText.toUpperCase();
		int key = keyLength;
		double num = 0;
		double[] total;
		String[] arrayOfSubstrings = new String[keyLength];
		for (int j = 0; j < arrayOfSubstrings.length; j++) {
			arrayOfSubstrings[j] = "";
		}
		for (int j = 0; j < arrayOfSubstrings.length; j++) {
			for (int i = j; i < cipherText.length(); i++) {
				arrayOfSubstrings[j] += cipherText.charAt(i);
				i += keyLength - 1;
			}
		}
		total = averageIndexOfCoincidence(arrayOfSubstrings);
		for (int j = 0; j < arrayOfSubstrings.length; j++) {
			num += total[j];
		}
		return num / key;
	}

	public static double[] averageIndexOfCoincidence(String[] x) { // index of
																	// coincidence
																	// for all
																	// the
																	// substrings
		double[] totalArray = new double[x.length]; // array of IOCs for all the
													// substrings
		for (int i = 0; i < x.length; i++) {
			totalArray[i] = result(x[i]); // x[i] pointing to one specific
											// substring from the
											// arrayOfSubstrings
			// System.out.println("this key: " + x[i]);
			// System.out.println("average of this key: " + totalArray[i]);
		}
		return totalArray;
	}

	public static double result(String x) { // index of coincidence for a
											// specific character in a given
											// substring
		double xyz = 0d;
		for (char i = 'A'; i <= 'Z'; i++) {
			xyz += individualIC(numberOfLetters(x, i));
			// System.out.println("HAHA TAKE THIS char : " + i + " yeah boi " +
			// xyz);
		}
		return xyz;
	}

	public static double individualIC(double[] x) { // for one character in a
													// given substring, taking
													// in numberofchars, count
													// of letters
		return (x[0] * (x[0] - 1)) / (x[1] * (x[1] - 1));
	}

	public static double[] numberOfLetters(String cipherText, char x) { // number
																		// of a
																		// specific
																		// letter
																		// in
																		// the
																		// given
																		// CT
																		// and
																		// the
																		// total
																		// count
																		// of
																		// letters
		Map<Character, Double> freq = new HashMap<Character, Double>();
		double totalCountOfLetters = 0;
		for (char xyz = 'A'; xyz <= 'Z'; xyz++) {
			freq.put(xyz, 0d);
		}
		for (int i = 0; i < cipherText.length(); i++) {
			char ch = Character.toUpperCase(cipherText.charAt(i));
			if ((ch >= 65 && ch <= 90)) {
				freq.put(ch, freq.get(ch) + 1);
				totalCountOfLetters = totalCountOfLetters + 1;
			}
		}
		double[] val = { freq.get(x).doubleValue(), totalCountOfLetters };
		return val;
	}
}