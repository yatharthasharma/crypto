package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all the helper functions that have been used throughout the tasks.
 * @author Yathartha Sharma
 *
 */
public class AnalyseText {
	/**
	 * 
	 * @param path path for the file which is to be used to set the text
	 * @param obj FrequencyAnalyser class object for which the text is to be set.
	 * 
	 * This function can be used to set the text for the given FrequencyAnalyser object. It takes in the path of the file from where
	 * you take in the text for the string.
	 */
	public static void Analysing(String path, FrequencyAnalyser obj) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path));
			String str = new String(bytes, StandardCharsets.UTF_8);				// make string from the file text
			obj.setText(str);													// set text
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method can be used to inspect a character - if it is in the valid array index 0-25 for any character array.
	 * 
	 * @param x character to inspect
	 * @return if the character is out of limits
	 */
	public static boolean outOfLimits(char x) {
		return (x > 25 || x < 0);
	}

	/**
	 * This method can be used to get the character which occurrs the maximum number of times in the given file.
	 * 
	 * @param relativePath path of the file relative to this class
	 * @return character which occurres the maximum number of times in the supplied file.
	 */
	public static char maxCharByFilePath(String relativePath) {
		double[] freqArray = new double[26]; 			// make an array containing values from frequency analysis.
		char[] charArray = new char[26]; 				// make an array which has all the characters from english language.
		double maxFreq = 0;								
		char maxChar = 0;
		FrequencyAnalyser w = new FrequencyAnalyser();	// make a FrequencyAnalyser class object which can be used to analyse the given file
		String mainPath;
		try {
			mainPath = Paths.get(AnalyseText.class.getResource("/").toURI()).toString();
			String plaintextFilePath = mainPath + relativePath;
			AnalyseText.Analysing(plaintextFilePath, w);	// Set text for the given FrequencyAnalyser object using Analysing method
			FrequencyTable x = w.analyse();
			freqArray = x.getTable();
			char c = 'A';
			for (int local = 0; local < 26; local++) {		// Fill charArray with characters
				charArray[local] = c;
				c++;
			}
			for (int local = 0; local < freqArray.length; local++) {	// get the character occuring maximum number of times
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

	/**
	 * This method can be used to get the character which occurrs the maximum number of times in the given text.
	 * 
	 * @param text from which you want to get the character that occurrs the most
	 * @return character that occurrs the most
	 */
	public static char maxCharByCipherText(String ciphertext) {
		double[] freqArray = new double[26]; // make an array containing values from frequency analysis.
		char[] charArray = new char[26]; // make an array which has all the characters from english language.
		double maxFreq = 0;
		char maxChar = 0;
		FrequencyAnalyser w = new FrequencyAnalyser();// make a FrequencyAnalyser class object which can be used to analyse the given file
		w.setText(ciphertext);					// set text given for the FrequencyAnalyser object we created
		FrequencyTable x = w.analyse();
		freqArray = x.getTable();
		char c = 'A';
		for (int local = 0; local < 26; local++) {// Fill charArray with characters
			charArray[local] = c;
			c++;
		}
		for (int local = 0; local < freqArray.length; local++) {// get the character occuring maximum number of times
			if (freqArray[local] > maxFreq) {
				maxFreq = freqArray[local];
				maxChar = charArray[local];
			}
		}
		return maxChar;
	}

	/**
	 * This method can be used to get keys for all the substrings in a Vigenere ciphertext.
	 * @param arrayOfSubstrings array of substrings from a ciphertext
	 * @return array of keys that can be used with substrings.
	 */
	public static char[] freqAnalysisForVigenere(String[] arrayOfSubstrings) {
		char maxCharInEnglishTexts = maxCharByFilePath("/res/pg1661.txt");	// get maximum occurring character from the sample file given for english letters
		char[] keys = new char[arrayOfSubstrings.length];				// array of key characters
		for (int i = 0; i < arrayOfSubstrings.length; i++) {			// iterate through the array of substrings to get the shifts by subtracting ascii codes of ciphertext chars and max occurring char in the sample text
			keys[i] = (char) (maxCharByCipherText(arrayOfSubstrings[i]) - maxCharInEnglishTexts);
		}
		return keys;
	}
	
	/**
	 * This method can be used to get substrings for a given ciphertext that is encrypted using Vigenere cipher and the key length used.
	 * 
	 * @param cipherText text encrypted with Vigenere for which substrings are needed
	 * @param keyLength	length of the key used to encrypt the text
	 * @return array of substrings
	 */
	public static String[] getSubstrings(String cipherText, int keyLength) {
		cipherText = cipherText.toUpperCase();
		String[] arrayOfSubstrings = new String[keyLength];
		for (int j = 0; j < arrayOfSubstrings.length; j++) {		// initialising the substrings
			arrayOfSubstrings[j] = "";
		}
		for (int j = 0; j < arrayOfSubstrings.length; j++) {		// add text to array of substrings
			for (int i = j; i < cipherText.length(); i++) {
				arrayOfSubstrings[j] += cipherText.charAt(i);
				i += keyLength - 1;
			}
		}
		return arrayOfSubstrings;
	}

	/**
	 * This method can be used to calculate IOC for a ciphertext given a certain keylength value
	 * 
	 * @param cipherText encrypted text for which IOC is to be calculated
	 * @param keyLength	keylength used for which you need the IOC to be calculated
	 * @return Index of Coincidence
	 */
	public static double indexOfCoincidence(String cipherText, int keyLength) {
		cipherText = cipherText.toUpperCase();
		int key = keyLength;
		double num = 0;
		double[] total;
		String[] arrayOfSubstrings = new String[keyLength];
		for (int j = 0; j < arrayOfSubstrings.length; j++) {// initialising the substrings
			arrayOfSubstrings[j] = "";
		}
		for (int j = 0; j < arrayOfSubstrings.length; j++) {// add text to array of substrings
			for (int i = j; i < cipherText.length(); i++) {
				arrayOfSubstrings[j] += cipherText.charAt(i);
				i += keyLength - 1;
			}
		}
		total = averageIndexOfCoincidence(arrayOfSubstrings);//calculate average index of coincidence for each substring
		for (int j = 0; j < arrayOfSubstrings.length; j++) {//get the total of all averages from substrings
			num += total[j];
		}
		return num / key;// return total of averages divided by the number of substrings (which is equal to the key length) to get the IOC
	}

	/**
	 * This method is used to calculate indices of coincidence for all the substrings supplied 
	 * @param x array of substrings
	 * @return array having IOCs for the given substrings
	 */
	public static double[] averageIndexOfCoincidence(String[] x) {
		double[] totalArray = new double[x.length]; // declare array of IOCs
		for (int i = 0; i < x.length; i++) {//iterating through the array of substrings
			totalArray[i] = result(x[i]); //getting IOC for one specific substring
		}
		return totalArray;//return array of IOCs
	}

	/**
	 * This method is used to calculate IOC for the substring supplied - done by adding individual characters' IOCs.
	 * @param x substring
	 * @return IOC of substring
	 */
	public static double result(String x) {
		double xyz = 0d;		// declare IOC
		for (char i = 'A'; i <= 'Z'; i++) {// iterate through the characters of substring
			xyz += individualIC(numberOfLetters(x, i));//add individual IOCs of characters
		}
		return xyz;//return IOC of the substring
	}

	/**
	 * This method is used to calculate IOC of an individual character.
	 * @param x array which has two values - number of times the character has occurred in the substring, and total number of character in the substring
	 * @return IOC of the char
	 */
	public static double individualIC(double[] x) {
		return (x[0] * (x[0] - 1)) / (x[1] * (x[1] - 1));
	}

	/**
	 * This method is used to get total number of times a letter has occurred in a specific text and the total number of chars there are in the said text.
	 * 
	 * @param cipherText text string
	 * @param x character which is to be counted
	 * @return array of values - number of times the char occurred, total number of chars in the text
	 */
	public static double[] numberOfLetters(String cipherText, char x) {
		Map<Character, Double> freq = new HashMap<Character, Double>();
		double totalCountOfLetters = 0;
		for (char xyz = 'A'; xyz <= 'Z'; xyz++) {// initialising map with values (A-Z, 0)
			freq.put(xyz, 0d);
		}
		for (int i = 0; i < cipherText.length(); i++) {// iterating through the given text to count the letters
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