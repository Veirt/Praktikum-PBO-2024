package posttest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
	public final static void clearConsole() {
		// final attribute
		final String os = System.getProperty("os.name");

		try {
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				// sa pake arch btw
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			System.out.println("Something went wrong when clearing the screen: " + e.getMessage());
		}
	}

	public static void enterToContinue(String message) {
		System.out.println(message);
		System.out.println("Press enter to continue.");
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			// apakah yang invoke error ini
		}

		clearConsole();
	}

	// This will return the "index" instead of the "numbering index", or -1 if an
	// error occurred.
	public static <T> int inputIndexInteractive(ArrayList<T> list) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int idx;
		while (true) {
			try {
				System.out.print("Masukkan nomor: ");
				idx = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				return -1;
			}

			break;
		}

		int res = idx - 1;
		if (idx < 0 || idx > list.size()) {
			return -1;
		}

		return res;
	}
}
