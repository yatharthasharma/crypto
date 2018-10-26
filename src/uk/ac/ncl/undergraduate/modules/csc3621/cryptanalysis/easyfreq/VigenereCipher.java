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
    	//plaintext = "newcastleuniversity";				// used hardcoded string for plaintext to check if the function is working correctly - now commented out
    	//key = "ncl";										// used hardcoded string for key to check if the function is working correctly - now commented out
    	plaintext = plaintext.toUpperCase();							// change plaintext chars to uppercase
    	key = key.toUpperCase();										// change key chars to uppercase
    	String ciphertext = "";
    	int keyLength = 0;
    	for (int i = 0; i < plaintext.length(); i++){					// iterating through the plaintext string
    		char ch = plaintext.charAt(i);								// handle characters one by one
    		char temp = (char)((ch + key.charAt(keyLength)) % 26);		// using the formula (p + k mod 26) for vigenere to encrypt
    		if (ch >= 65 && ch <= 90){									// if the character is in range A-Z
    			if (AnalyseText.outOfLimits(temp)){						// if the character encrypted is out of range 0-25 then subtract 26 from it
    				temp -= 26;
    			}
    			ciphertext += (char)(temp + 'A');						// add ASCII value for 'A' to character to get it in the range of A-Z and concatenate ciphertext string.
    			keyLength = ++keyLength % key.length();					// change key character to the one which is to be used for encryption next
    		}else{
    			ciphertext += ch;										// if the character is not in range A-Z add it to the ciphertext string right away
    		}
    	}
    	return ciphertext;												// return ciphertext
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
    	char c = 'A';
    	char[] charArray = new char[26];
    	for (int local = 0; local < 26; local++) {
    		charArray[local] = c;
    		c++;										//code up till here declares a character array 'charArray' which stores the english characters A - Z.
    	}												
    	//ciphertext = "aghpcdgnphptigcfkel";			// used hardcoded string for ciphertext to check if the function is working correctly - now commented out
    	ciphertext = ciphertext.toUpperCase();			// convert ciphertext chars to uppercase
    	//key = "ncl";									// used hardcoded string for key to check if the function is working correctly - now commented out
    	key = key.toUpperCase();									// convert key chars to uppercase
    	String plaintext = "";										
    	int keyLength = 0;											// counter which helps us retrieve the correct character from the key to decrypt the message
    	for (int i = 0; i < ciphertext.length(); i++){				// iterating through the ciphertext string's characters to decrypt
    		char ch = ciphertext.charAt(i);							// handling characters one by one
    		char temp = (char)((ch - key.charAt(keyLength)) % 26);	// vigenere cipher decryption -> plaintext = ciphertext - key mod 26
    		if (ch >= 65 && ch <= 90){								// if character is in the range A - Z (ASCII codes)
    			if (AnalyseText.outOfLimits(temp)){					// if temp is out of limits of the charArray index (0-25) then add 26
    				temp += 26;
    			}
    			plaintext += charArray[temp];						// add the character just decrypted to plaintext - character is taken from charArray using temp as the index to get correct letter
    			//plaintext += (char)(temp + 'A');					// one alternate way of doing the same thing - add 'A' to get temp into the range of ASCII codes for letters
    			keyLength = ++keyLength % key.length();				// increase the key counter by one and then check if its greater than the key length. If so, bring it back to the range (0 - key length)				
    		}else{
    			plaintext += ch;									// if character is not in the range (A - Z) add it to plaintext string right away
    		}
    	}
	    return plaintext;												// return plaintext string
        //</editor-fold> // END OF YOUR CODE
    }

}
