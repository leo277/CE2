/**
 * This is user-defined comparator. This class will allow 
 * the two strings to be compared with case-insensitivity. 
 *
 * @project: TextBuddy ++ 
 * @author : Zhang yongkai
 * @matric : A0110567L
 * @tutorialID : T11
 * 
 */

import java.util.Comparator;

public class MyComparator implements Comparator<String> {
	public int compare(String textA, String textB) {
		return textA.compareToIgnoreCase(textB);
	}

}
