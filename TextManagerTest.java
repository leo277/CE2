/**
 * This is the Junit test for the class TextManager. 
 * pre-condition: all the input should be valid, i.e the arguments of delete, search, add is valid 
 * because the arguments is checked in the main TextBuddy.java class. 
 * 
 * @project: TextBuddy ++ 
 * @author : Zhang yongkai
 * @matric : A0110567L
 * @tutorialID : T11
 * 
 */

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextManagerTest {
	private TextManager manager;
	private static final String fileName = "test.txt";
	private static final String ADD_MESSAGE = "added to %1$s: ";
	private static final String CLEAR_MESSAGE = "all content deleted from %1$s";
	private static final String EMPTY_MESSAGE = "%1$s is empty";
	private static final String DELETE_MESSAGE = "deleted from %1$s: ";
	private static final String INVALID_DELETE_MESSAGE = "cannot delete such element";

	@Before
	public void setUp() throws Exception {
		System.out.println("Run @Before");
		manager = new TextManager(fileName);
		manager.clearFile();
	}

	@Test
	public final void testMethods() throws IOException {

		// testing add method
		performCommand("Testing Add",
				generateExpectedOutput(ADD_MESSAGE, fileName) + "\"apple\"",
				manager.add("apple"));
		performCommand("Testing Add",
				generateExpectedOutput(ADD_MESSAGE, fileName) + "\"pear\"",
				manager.add("pear"));
		performCommand("Testing Add",
				generateExpectedOutput(ADD_MESSAGE, fileName) + "\"x-men\"",
				manager.add("x-men"));
		performCommand("Testing Add",
				generateExpectedOutput(ADD_MESSAGE, fileName)
						+ "\"Junit testing successful\"",
				manager.add("Junit testing successful"));
		performCommand("Testing Display",
				"1. apple\n2. pear\n3. x-men\n4. Junit testing successful",
				manager.display());
		performCommand("Testing Add", "nothing to add", manager.add(""));

		// testing clearFile method
		performCommand("Testing clear",
				generateExpectedOutput(CLEAR_MESSAGE, fileName),
				manager.clearFile());

		// testing empty file display
		performCommand("Testing displaying empty file",
				generateExpectedOutput(EMPTY_MESSAGE, fileName),
				manager.display());

		// testing delete method
		manager.add("marvel vs capcom 3");
		manager.add("GTA 5 is coming");
		manager.add("Ironman is fighting superman");
		performCommand("Testing delete",
				generateExpectedOutput(DELETE_MESSAGE, fileName)
						+ "\"GTA 5 is coming\"", manager.delete("2"));
		performCommand("Testing invalid delete", INVALID_DELETE_MESSAGE,
				manager.delete("0"));
		performCommand("Testing invalid delete", INVALID_DELETE_MESSAGE,
				manager.delete("5"));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Run @After");
	}

	// pseudo methods
	private void performCommand(String description, String expected,
			String command) {
		assertEquals(description, expected, command);
	}

	// other methods
	private String generateExpectedOutput(String input, String parameter) {
		return String.format(input, parameter);
	}

}
