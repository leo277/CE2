import java.util.Comparator;


public class MyComparator implements Comparator<String>{
	public int compare(String textA, String textB){
		return textA.compareToIgnoreCase(textB);
	}

}
