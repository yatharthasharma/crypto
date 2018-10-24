package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is to compute a frequency table of a texts.
 *
 * @author Changyu Dong
 * @author Roberto Metere
 * @author Yathartha Sharma
 */
public class FrequencyAnalyser {

	/**
	 * The text to analyse
	 */
	private String text;

	/**
	 * Get the text to analyse.
	 *
	 * @return the text to analyse.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set the text to analyse.
	 *
	 * @param text
	 *            the text to analyse.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * This method returns a frequency table as a result of the analysis of the
	 * text.
	 *
	 * TODO: complete the function that conduct a frequency analysis of the
	 * internal buffer and produce a frequency table based on the analysis.
	 * Please, write your code between the comments as appropriate.
	 *
	 * @return frequency table as a result of the analysis of the text
	 */
	public FrequencyTable analyse() { // throws IOExecption
		// Please, do not remove the editor-fold comments.
		// <editor-fold defaultstate="collapsed" desc="Write your code here
		// below!">
		FrequencyTable tableOfFreq = new FrequencyTable(); // declaring
															// FrequencyTable
															// object that is to
															// be returned
		int totalCountOfLetters = 0;
		Map<Character, Double> freq = new HashMap<Character, Double>();
		String str = this.getText();
		for (char x = 'A'; x <= 'Z'; x++) {
			freq.put(x, 0d);
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = Character.toUpperCase(str.charAt(i));
			if ((ch >= 65 && ch <= 90)) {
				freq.put(ch, freq.get(ch) + 1);
				totalCountOfLetters++;
			}
		}
		for (char key : freq.keySet()) {
			tableOfFreq.setFrequency(key, freq.get(key).doubleValue() / totalCountOfLetters); // calculating
																								// frequency
		}
		return tableOfFreq;

		// </editor-fold> // END OF YOUR CODE
	}

}