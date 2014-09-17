/**
 * CE2 2014 
 * This is the TextBuddy++ program. It is a command line program which
 * is used to manipulate a text file. 
 * Users can add a task, display tasks, delete a task, 
 * clear the tasks and exit the program.
 *
 * This program saves after every operation.
 * This is also the improved version of TextBuddy. This program now
 * adds two additional functions, namely, sort and search functions.
 *  
 * @project: TextBuddy ++ 
 * @author : Zhang yongkai
 * @matric : A0110567L
 * @tutorialID : T11
 */

import java.io.IOException;
import java.util.Scanner;

public class TextBuddy {

	private static final String WELCOME_MESSAGE = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String WRONG_COMMAND_MESSAGE = "Sorry, please enter a valid command. Thank you";
	private static final String INVALID_FILENAME_MESSAGE = "Invalid file name";
	private static final String ERROR_INVALID_ARGUMENT = "Invalid arguement";
	private static TextManager manager = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			checkArgumentValidity(args);
			String fileName = args[0];
			printWelcomeMessage(fileName);
			manager = new TextManager(fileName);
			run();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void run() throws IOException {
		String input = null;
		do {
			System.out.print("command: ");
			input = scanner.nextLine().trim();
			try {
				showMessage(processCommand(input));
			} catch (NumberFormatException e) {
				showMessage(ERROR_INVALID_ARGUMENT);
			}
		} while (true);
	}

	public static String processCommand(String input) throws IOException {
		String[] inputs = input.split("\\s+");
		String command = inputs[0];
		String content = input.replaceFirst(command, "").trim();

		switch (command) {
		case "add":
			return manager.add(content);

		case "display":
			return manager.display();

		case "delete":
			return manager.delete(content);

		case "clear":
			return manager.clearFile();

		case "sort":
			return manager.sortByAlphabet();

		case "search":
			return manager.search(content);

		case "exit":
			manager.exit();

		default:
			return (WRONG_COMMAND_MESSAGE);
		}

	}

	private static void checkArgumentValidity(String[] args) {
		if (args.length <= 0) {
			showMessage(INVALID_FILENAME_MESSAGE);
			System.exit(0);
		}
	}

	private static void printWelcomeMessage(String fileName) {
		showMessage(String.format(WELCOME_MESSAGE, fileName));
	}

	private static void showMessage(String input) {
		System.out.println(input);
	}
}