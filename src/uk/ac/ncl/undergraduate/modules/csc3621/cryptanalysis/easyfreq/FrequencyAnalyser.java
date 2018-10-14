package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
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
     * @param text the text to analyse.
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
    public FrequencyTable analyse(){						// throws IOExecption
        // Please, do not remove the editor-fold comments.
        //<editor-fold defaultstate="collapsed" desc="Write your code here below!">
		byte[] bytes;
		FrequencyTable tableOfFreq = new FrequencyTable();						// declaring FrequencyTable object that is to be returned
		int totalCountOfLetters = 0;
		Map <Character, Double> freq = new HashMap<Character, Double>();
		try {
			bytes = Files.readAllBytes(Paths.get("/home/yathartha/eclipse-workspace/csc3621-coursework-1/src/res/pg1661.txt"));			// input sample text from the given file
			String str = new String(bytes, StandardCharsets.UTF_8);
//			for (int i = 0; i < str.length(); i++) {
//				char ch = str.charAt(i);
//				//System.out.print(ch);
//				if (ch == 65 || ch == 97) {
//					totalCountOfLetters++;
//					table[0]++;
//				}else if (ch == 66 || ch == 98) {
//					table[1]++;
//					totalCountOfLetters++;
//				}else if (ch == 67 || ch == 99) {
//					table[2]++;
//					totalCountOfLetters++;
//				}else if (ch == 68 || ch == 100) {
//					table[3]++;
//					totalCountOfLetters++;
//				}else if (ch == 69 || ch == 101) {
//					table[4]++;
//					totalCountOfLetters++;
//				}else if (ch == 70 || ch == 102) {
//					table[5]++;
//					totalCountOfLetters++;
//				}else if (ch == 71 || ch == 103) {
//					tableOfFreq.getTable()[6]++;
//					table[6]++;
//					totalCountOfLetters++;
//				}else if (ch == 72 || ch == 104) {
//					tableOfFreq.getTable()[7]++;
//					table[7]++;
//					totalCountOfLetters++;
//				}else if (ch == 73 || ch == 105) {
//					tableOfFreq.getTable()[8]++;
//					table[8]++;
//					totalCountOfLetters++;
//				}else if (ch == 74 || ch == 106) {
//					table[9]++;
//					totalCountOfLetters++;
//				}else if (ch == 75 || ch == 107) {
//					table[10]++;
//					totalCountOfLetters++;
//				}else if (ch == 76 || ch == 108) {
//					table[11]++;
//					totalCountOfLetters++;
//				}else if (ch == 77 || ch == 109) {
//					tableOfFreq.getTable()[12]++;
//					table[12]++;
//					totalCountOfLetters++;
//				}else if (ch == 78 || ch == 110) {
//					tableOfFreq.getTable()[13]++;
//					table[13]++;
//					totalCountOfLetters++;
//				}else if (ch == 79 || ch == 111) {
//					tableOfFreq.getTable()[14]++;
//					table[14]++;
//					totalCountOfLetters++;
//				}else if (ch == 80 || ch == 112) {
//					tableOfFreq.getTable()[15]++;
//					table[15]++;
//					totalCountOfLetters++;
//				}else if (ch == 81 || ch == 113) {
//					tableOfFreq.getTable()[16]++;
//					table[16]++;
//					totalCountOfLetters++;
//				}else if (ch == 82 || ch == 114) {
//					tableOfFreq.getTable()[17]++;
//					table[17]++;
//					totalCountOfLetters++;
//				}else if (ch == 83 || ch == 115) {
//					tableOfFreq.getTable()[18]++;
//					table[18]++;
//					totalCountOfLetters++;
//				}else if (ch == 84 || ch == 116) {
//					table[19]++;
//					totalCountOfLetters++;
//				}else if (ch == 85 || ch == 117) {
//					table[20]++;
//					totalCountOfLetters++;
//					tableOfFreq.getTable()[21]++;
//				}else if (ch == 86 || ch == 118) {
//					table[21]++;
//					totalCountOfLetters++;
//				}else if (ch == 87 || ch == 119) {
//					table[22]++;
//					totalCountOfLetters++;
//				}else if (ch == 88 || ch == 120) {
//					table[23]++;
//					totalCountOfLetters++;
//				}else if (ch == 89 || ch == 121) {
//					table[24]++;
//					totalCountOfLetters++;
//				}else if (ch == 90 || ch == 122) {
//					table[25]++;
//					totalCountOfLetters++;
//				}else {}
//			}
			for (char x = 'A'; x <= 'Z'; x++) {
				freq.put(x, 0d);
			}
			for (int i = 0; i < str.length(); i++) {
				char ch = Character.toUpperCase(str.charAt(i));
				if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
					freq.put(ch, freq.get(ch) + 1);
					totalCountOfLetters++;	
				}
			}
			System.out.println("Total letters is: " + totalCountOfLetters);
			for (int i = 65; i < 91; i++) {
				char x = (char)i;
				System.out.print(freq.get(x) + " ");
			}
			for (char key : freq.keySet()) {
				tableOfFreq.setFrequency(key, freq.get(key).doubleValue()/totalCountOfLetters);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableOfFreq;
		
        //</editor-fold> // END OF YOUR CODE
    }

}
