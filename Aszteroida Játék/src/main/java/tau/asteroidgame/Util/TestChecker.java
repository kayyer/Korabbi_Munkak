package tau.asteroidgame.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestChecker {
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Error: Invalid amout of parameters!");
			System.out.println("Usage: runTests.bat <oututGot> <expectedOutput>");
			return;
		}
		try {
			
			String[] expectedLines = Files.readString(Path.of(args[0])).split(System.lineSeparator());
			String[] gotLines = Files.readString(Path.of(args[1])).split(System.lineSeparator());
			
			int minLineNum = Math.min(expectedLines.length, gotLines.length);
			String[] maxLine = expectedLines.length < gotLines.length ? gotLines : expectedLines;
			
			for (int i = 0; i < minLineNum; i++) {
				// Nemdeterminisztikus teszteset
				String[] possibilities = expectedLines[i].split("/");
				boolean match = false;
				for (int j = 0; j < possibilities.length; j++) {
					if (possibilities[j].strip().equals(gotLines[i])) {
						match = true;
						break;
					}
				
				}
				
				
				
				if (match)
					System.out.println(gotLines[i]);
				else {
					System.out.println("ERROR!\nExpected:");
					System.out.println(expectedLines[i]);
					System.out.println("Got:");
					System.out.println(gotLines[i]);
				}
			}
			
			if (minLineNum != maxLine.length) {
				System.out.println("Gotten one had " + (maxLine == gotLines ? "more " : "less ") + "lines than expected.\nRemaining lines:");
				for (int i = minLineNum; i < maxLine.length; i++) {
					System.out.println(maxLine[i]);
				}
			}
			
		} catch (IOException e) {
			System.out.println("ERROR Reading File. Exiting");
			e.printStackTrace();
		}
		
	}

}
