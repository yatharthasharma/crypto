package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is for frequency cryptanalysis of ciphertext.
 *
 * @author Changyu Dong
 * @author Roberto Metere
 * @author Yathartha Sharma
 */
public class VigenereCryptanalysis {

	/**
	 * The ciphertext (encryption of the plaintext).
	 */
	private String ciphertext;

	/**
	 * The plaintext (readable content).
	 */
	private String plaintext;

	/**
	 * The key such that the encryption of the plaintext with such key gives the
	 * ciphertext.
	 */
	private final StringBuffer key = new StringBuffer();

	/**
	 * This variable is just to run the script interactive, that is with manual
	 * tunes.
	 */
	private boolean interactive;

	/**
	 * INTERACTIVE means that you can manually tune the analysis and/or the
	 * result.
	 */
	public static final boolean INTERACTIVE = true;

	/**
	 * AUTOMATIC means that the analysis will not ask any further information.
	 */
	public static final boolean AUTOMATIC = false;

	/**
	 * Create an new class to cryptanalyze texts.
	 */
	public VigenereCryptanalysis() {
	}

	/**
	 * Constructor with interactive choice.
	 *
	 * @param interactive
	 *            whether it should ask for manual tuning or not
	 */
	public VigenereCryptanalysis(boolean interactive) {
		this.interactive = interactive;
	}

	/**
	 * Set the ciphertext to analyse.
	 *
	 * @param text
	 *            the text to set as
	 */
	public void setCiphertext(String text) {
		this.ciphertext = text;
	}

	/**
	 * This method is to allow you to manually set the key can be used as a
	 * subroutine in your cryptanalysis for manual adjustment
	 */
	private void manualAdjustment() {

		int answer;
		int index;
		char letter;

		do {
			System.out.println("How do you want to change the key (1: insert, 2:replace, 3:delete, 4:nothing)? ");
			answer = Util.reader.nextInt();
		} while (answer < 1 || answer > 4);

		switch (answer) {
		case 1:
			System.out.println("Enter the index where you want to insert the key charater");
			index = Util.reader.nextInt();
			System.out.println("Enter the letter you want to insert");
			letter = Util.reader.next().charAt(0);
			if (index < 0 || index > this.key.length()) {
				System.out.println("Index out of range");
			} else if (!Util.isValidLetter(letter)) {
				System.out.println("key character must be a letter");
			} else {
				this.key.insert(index, letter);

			}
			break;

		case 2:
			System.out.println("Enter the index of the character you want to replace");
			index = Util.reader.nextInt();
			System.out.println("Enter the new character");
			letter = Util.reader.next().charAt(0);
			if (index < 0 || index >= this.key.length()) {
				System.out.println("Index out of range");
			} else if (!Util.isValidLetter(letter)) {
				System.out.println("key character must be a letter");
			} else {
				this.key.replace(index, index, Character.toString(letter));
			}
			break;

		case 3:
			System.out.println("Enter the index of the character you want to delete");
			index = Util.reader.nextInt();
			if (index < 0 || index >= this.key.length()) {
				System.out.println("Index out of range");
			} else {

				this.key.deleteCharAt(index);

			}
			break;

		default:
			break;
		}
	}

	/**
	 * This method conducts cryptanalysis of the frequency of letters in the
	 * ciphertext to retrieve the encryption key.
	 *
	 * <p>
	 * TODO:
	 * <ul>
	 * <li>Conduct a frequency analysis of the internal buffer.
	 * <li>Find the key. You should try your best to find the key based on your
	 * analysis.
	 * <li>Store the key in the class variable <code>this.key</code>.
	 * </ul>
	 *
	 * <p>
	 * Manual adjustment in the method is allowed but needs to be justified in
	 * your report. You can create methods as you like.
	 *
	 * @return the key as result of the cryptanalysis
	 */
	public String cryptanalysis() {
		// Please, do not remove the editor-fold comments.
		// <editor-fold defaultstate="collapsed" desc="Write your code here
		// below!">
		VigenereCryptanalysis v = new VigenereCryptanalysis();
		FrequencyAnalyser w = new FrequencyAnalyser();
		String mainPathForCipherText;
		double IOCDiff;
		double[] IOCs = new double[9];
		byte[] bytes;
		int keyLength;
		try {
			mainPathForCipherText = Paths.get(FrequencyCryptanalysis.class.getResource("/").toURI()).toString();
			String cipherPlaintextFilePath = mainPathForCipherText + "/res/Exercise2Ciphertext.txt";
			// AnalyseText.Analysing(cipherPlaintextFilePath, w);
			bytes = Files.readAllBytes(Paths.get(cipherPlaintextFilePath)); // input text from the file
			String cipherText = new String(bytes, StandardCharsets.UTF_8);
			for (int i = 0; i < 9; i++){
				IOCs[i] = AnalyseText.indexOfCoincidence(cipherText, i+2);
				System.out.println("This is for key length " + (i+2) + ": " + IOCs[i]);
			}
			IOCDiff = IOCs[0];
			keyLength = 2;
			for (int i = 0; i < 8; i++){
				if (Math.abs(0.067 - IOCs[i+1]) < Math.abs(0.067 - IOCs[i])){
					IOCDiff = IOCs[i+1];
					keyLength++;
				}
			}
			for (int i = 0; i < 9; i++){
				if (IOCDiff == AnalyseText.indexOfCoincidence(cipherText, i+2)){
					keyLength = i + 2;
				}
			}
			System.out.println("What's the key length? " + keyLength + " and IOC: " + IOCDiff);
			for (int i = 0; i < keyLength; i++){
				this.key.append("A");
			}
			// set path and key and get index of coincidences.

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// </editor-fold> // END OF YOUR CODE
		// The following code allows you to manually adjust your result.
		if (this.interactive) {
			String answer;
			do {

				do {
					System.out.println("Do you want to see the plaintext (Y/N)? ");
					answer = Util.reader.next().toUpperCase();
				} while (!(answer.equals("Y") || answer.equals("N")));

				if (answer.equals("Y")) {
					this.decrypt();
					System.out.println(this.plaintext);
				}

				do {
					System.out.println("Do you want to see the key (Y/N)? ");
					answer = Util.reader.next().toUpperCase();
				} while (!(answer.equals("Y") || answer.equals("N")));

				if (answer.equals("Y")) {
					System.out.println(this.key);
				}

				do {
					System.out.println("Do you want to change the key (Y/N)? ");
					answer = Util.reader.next().toUpperCase();
				} while (!(answer.equals("Y") || answer.equals("N")));

				if (answer.equals("Y")) {
					this.manualAdjustment();
				}

				do {
					System.out.println("Do you want to stop (Y/N)? ");
					answer = Util.reader.next().toUpperCase();
				} while (!(answer.equals("Y") || answer.equals("N")));

			} while (!answer.equals("Y"));
		}

		return this.key.toString();
	}

	/**
	 * This method reconstructs the plaintext from the ciphertext with the key.
	 */
	public void decrypt() {
		this.plaintext = VigenereCipher.decrypt(this.ciphertext, this.key.toString());
	}

	/**
	 * Show the results of the complete analysis.
	 */
	public void showResult() {
		System.out.println("The key is " + this.key.toString());
		this.decrypt();
		System.out.println("The plaintext is:");
		System.out.println(this.plaintext);
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws java.io.IOException
	 *             errors reading from files
	 * @throws java.net.URISyntaxException
	 *             Errors in retrieving resources
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {
		String mainPath, ciphertextFilePath, ciphertext;
		VigenereCryptanalysis cryptanalysis;
		File solutionDirectory;
		String solutionKeyFilePath, solutionPlaintextFilePath;

		// Add argument -i at run to enable interactive mode (and disable
		// automatic mode)
		if (0 < args.length && args[0].equals("-i")) {
			cryptanalysis = new VigenereCryptanalysis(INTERACTIVE);
		} else {
			cryptanalysis = new VigenereCryptanalysis(AUTOMATIC);
		}

		// Get resources
		mainPath = Paths.get(FrequencyCryptanalysis.class.getResource("/").toURI()).toString();
		ciphertextFilePath = mainPath + "/res/Exercise2Ciphertext.txt";
		solutionDirectory = new File(mainPath + "/solution2");
		solutionKeyFilePath = solutionDirectory + "/key.txt";
		solutionPlaintextFilePath = solutionDirectory + "/plaintext.txt";

		// Do the job
		ciphertext = Util.readFileToBuffer(ciphertextFilePath);
		cryptanalysis.setCiphertext(ciphertext);
		cryptanalysis.cryptanalysis();
		cryptanalysis.showResult();

		// Write solution in res path
		if (!solutionDirectory.exists()) {
			solutionDirectory.mkdir();
		}
		Util.printBufferToFile(cryptanalysis.key.toString(), solutionKeyFilePath);
		Util.printBufferToFile(cryptanalysis.plaintext, solutionPlaintextFilePath);
	}
}
