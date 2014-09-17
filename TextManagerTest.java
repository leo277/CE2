import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextManagerTest {
	private TextManager manager;
	private static final String fileName = "test.txt";
	private static final String ADD_MESSAGE = "added to %1$s: ";

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

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Run @After");
		// manager.clearFile();
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