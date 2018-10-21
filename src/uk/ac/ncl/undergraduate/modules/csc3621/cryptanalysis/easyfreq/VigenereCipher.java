package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

/**
 * This class is capable of encrypt and decrypt according to the Vigen&egrave;re
 * cipher.
 *
 * @author Changyu Dong
 * @author Roberto Metere
 * @author Yathartha Sharma
 */
public class VigenereCipher {

    /**
     * Encryption function of the Vigen&egrave;re cipher.
     *
     * <p>
     * TODO: Complete the Vigen&egrave;re encryption function.
     *
     * @param plaintext the plaintext to encrypt
     * @param key the encryption key
     * @return the ciphertext according with the Vigen&egrave;re cipher.
     */
    public static String encrypt(String plaintext, String key) {
        // Please, do not remove the editor-fold comments.
        //<editor-fold defaultstate="collapsed" desc="Write your code here below!">
    	plaintext = "newcastleuniversity";
    	plaintext = plaintext.toUpperCase();
    	key = "NCL";
    	key = key.toUpperCase();
    	String ciphertext = "";
    	int keyLength = 0;
    	for (int i = 0; i < plaintext.length(); i++){
    		char ch = Character.toUpperCase(plaintext.charAt(i));
    		if (ch >= 65 && ch <= 90){
    			ciphertext += (char)((ch + key.charAt(keyLength)) % 26);
    			keyLength = ++keyLength % key.length();
    		}else{
    			ciphertext += ch;
    		}
    	}
    	//ciphertext = new String(characterArray);
    	System.out.println("THIS IS CIPHERTEXT: " + ciphertext);
    	return ciphertext;
        //</editor-fold> // END OF YOUR CODE
    }

    /**
     * Decryption function of the Vigen&egrave;re cipher.
     *
     * <p>
     * TODO: Complete the Vigen&egrave;re decryption function.
     *
     * @param ciphertext the encrypted text
     * @param key the encryption key
     * @return the plaintext according with the Vigen&egrave;re cipher.
     */
    public static String decrypt(String ciphertext, String key) {
        // Please, do not remove the editor-fold comments.
        //<editor-fold defaultstate="collapsed" desc="Write your code here below!">
    	/*char c = 'A';
    	char[] charArray = new char[26];
    	for (int local = 0; local < 26; local++) {
    		charArray[local] = c;
    		c++;
    	}*/
    	ciphertext = "aghpcdgnphptigcfkel";
    	ciphertext = ciphertext.toUpperCase();
    	key = "ncl";
    	key = key.toUpperCase();
    	String plaintext = "";
    	int keyLength = 0;
    	for (int i = 0; i < ciphertext.length(); i++){
    		char ch = ciphertext.charAt(i);
    		if (ch >= 65 && ch <= 90){
    			plaintext += (char)((ch - key.charAt(keyLength)) % 26);
    			keyLength = ++keyLength % key.length();
    		}else{
    			plaintext += ch;
    		}
    	}
    	return plaintext;
        //</editor-fold> // END OF YOUR CODE
    }

}
