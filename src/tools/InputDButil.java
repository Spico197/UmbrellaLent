package tools;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputDButil {
	static Scanner scan = new Scanner(System.in);
	public static int nextInt() {
		return scan.nextInt();
	}
	public static String next() {
		return scan.next();
	}
	public static String nextLine() {
		return scan.nextLine();
	}
	public static BigDecimal nextBigDecimal() {
		return scan.nextBigDecimal();
	}
}
