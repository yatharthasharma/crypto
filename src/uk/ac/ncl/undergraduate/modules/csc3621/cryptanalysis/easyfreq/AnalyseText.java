package uk.ac.ncl.undergraduate.modules.csc3621.cryptanalysis.easyfreq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnalyseText {
	public void Analysing(String path, FrequencyAnalyser obj) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path)); // input sample text from the given file
			String str = new String(bytes, StandardCharsets.UTF_8);
			obj.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// freq of english letters from pg1661.txt for ex1part1
	public static int searchIndex(char[] a, char target){
			for (int i = 0; i < a.length; i++)
				if (a[i] == target)
					return i;
			return -1;
		}
}