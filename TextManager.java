import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


class TextManager {

	private static final String EMPTY_MESSAGE = "%1$s is empty";
	private static final String ADD_MESSAGE = "added to %1$s: \"%2$s\"";
	private static final String NO_MESSAGE = "nothing to add";
	private static final String CLEAR_MESSAGE = "all content deleted from %1$s";
	private static final String INVALID_DELETE_MESSAGE = "cannot delete such element";
	private static final String DELETE_MESSAGE = "deleted from %1$s: \"%2$s\"";


	private File textFile = null;
	private String fileName = null;
	private ArrayList<String> tasks = new ArrayList<String>();

	// constructor
	
	
	public TextManager(String rawFileName) throws IOException {
		fileName = rawFileName;
		textFile = new File(fileName);
		textFile.createNewFile();
		readFile(getFileName());
	}

	public void readFile(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String currLine = reader.readLine();
		while (currLine != null) {
			tasks.add(currLine);
			currLine = reader.readLine();
		}
		reader.close();
	}

	// Accessors
	public String getFileName() {
		return this.fileName;
	}

	
	
	// public methods
	public String add(String content) throws IOException {
		if (!content.isEmpty()) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					getFileName(), true));
			tasks.add(content);
			writer.write(content);
			writer.newLine();
			writer.close();
			return (String.format(ADD_MESSAGE, getFileName(), content));
		} else {
			return (NO_MESSAGE);
		}
	}

	public String display() throws IOException {
		String content = "";
		if (tasks.isEmpty()) {
			return (String.format(EMPTY_MESSAGE, getFileName()));
		}
		for (int i = 0; i < tasks.size(); i++) {
			content += ((i + 1) + ". " + tasks.get(i)+"\n");
		}
		return content.trim();
	}

	public String clearFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(getFileName()));
		writer.write("");
		writer.close();
		tasks.clear();
		return (String.format(CLEAR_MESSAGE, getFileName()));
	}

	public String delete(String lineIndexToDelete) throws IOException {
		int deleteIndex = Integer.parseInt(lineIndexToDelete) - 1;
		if (deleteIndex >= tasks.size() || deleteIndex < 0) {
			return (INVALID_DELETE_MESSAGE);
		}
		String strDeleted = tasks.remove(deleteIndex);
		saveToFile();
		return (String.format(DELETE_MESSAGE, getFileName(), strDeleted));
	}


	public void saveToFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(getFileName()));
		for (int i = 0; i < tasks.size(); i++) {
			writer.write(tasks.get(i));
			writer.newLine();
		}
		writer.close();
	}

	public void exit() {
		System.exit(0);
	}

}