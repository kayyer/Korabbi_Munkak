package tau.asteroidgame.Util;

import java.util.Scanner;


public class Input {
	private static Scanner input = new Scanner(System.in);

	
	public static boolean getBooleanInput(String message) {
		
		
		System.out.print(message + ": (I/N) ");
		
		boolean inputParsedSuccessfully = false;
		boolean retVal = false;
		do	 {
			String readKeyword = input.nextLine().strip().toLowerCase();
			
			if (readKeyword.equals("y") || readKeyword.equals("i")) {
				inputParsedSuccessfully = true;
				retVal = true;
			}
			else if (readKeyword.equals("n")) {
				inputParsedSuccessfully = true;
				retVal = false;
			}
			else {
				System.out.print("Hibás bemenet! Adjon meg I/N (Y/N)-t ! ");
			}
		} while (!inputParsedSuccessfully);
		
		
		return retVal;
	}
	
	public static int getUnsignedIntInput(String message) {
		
		
		System.out.print(message + ": (Nemnegatív egész szám) ");
		
		boolean inputParsedSuccessfully = false;
		int retVal = -1;
		do {
			String readKeyword = input.nextLine().strip();
			
			try {
				retVal = Integer.parseInt(readKeyword);
				
				if (retVal >= 0) {
					inputParsedSuccessfully = true;
				}
			} catch (Exception e) {}
			finally {
				if (!inputParsedSuccessfully)
					System.out.print("Hibás bemenet! Adjon meg Nemnegatív egész számot ! ");
			}
		} while (!inputParsedSuccessfully);
		
		
		return retVal;
	}
	

	private Input(){}
}
