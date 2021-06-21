package tau.asteroidgame.Util;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import tau.asteroidgame.Game;

public class Logger {
	private static String indentation = "";
	private static Map<Object, String> objectMap = new HashMap<Object, String>();
	private static Map<String, Integer> nameUsageCounter = new HashMap<String, Integer>();
	private static Stack<String> callerNameStack = new Stack<String>();
	private static Stack<String> registerNameStack = new Stack<String>();
	private static Scanner input = new Scanner(System.in);
	
	private static Object nextRegisterCreator = null;
	private static Object[] nextRegisterCreationParams = null;
	
	private static String getParameterName(Object parameter) {
		String parameterName = null;
		
		if (parameter == null) {
			parameterName = "[]";
		}
		else {
			parameterName = objectMap.get(parameter);
			if (parameterName == null) {
				parameterName = parameter.toString();
			}
		}
		return parameterName;
	}
	
	public static boolean getBooleanInput(String message) {
		
		
		System.out.print(indentation + message + ": (I/N) ");
		
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
				System.out.print(indentation + "Hibás bemenet! Adjon meg I/N (Y/N)-t ! ");
			}
		} while (!inputParsedSuccessfully);
		
		
		return retVal;
	}
	
	public static int getUnsignedIntInput(String message) {
		
		
		System.out.print(indentation + message + ": (Nemnegatív egész szám) ");
		
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
					System.out.print(indentation + "Hibás bemenet! Adjon meg Nemnegatív egész számot ! ");
			}
		} while (!inputParsedSuccessfully);
		
		
		return retVal;
	}
	
	/**
	 * Nevet ad az objektumnak paraméteren keresztül, amit a Logger használ kiírásnál. Ha ez a név már használt, akkor számot kap a végére, hogy hányadik ilyen nevű (pl. r már volt, ezért most r2 lesz)
	 * 
	 * @param object Regisztrálandó objektum
	 * @param name A név, amit kapni fog (esetleg számozva, ha már van ilyen használva)
	 */
	public static void registerObject(Object object, String name) {
		Integer nameUsageCount = nameUsageCounter.get(name);
		if (nameUsageCount == null) {
			objectMap.put(object, name);
			nameUsageCounter.put(name, 1);
		}
		else {
			objectMap.put(object, name  + Integer.toString((++nameUsageCount)));
			nameUsageCounter.put(name, nameUsageCount);
		}
		
		if (nextRegisterCreator != null) {
			StringBuilder sb = new StringBuilder();
			
			
			sb.append(indentation).append(objectMap.get(nextRegisterCreator)).append("->").append(objectMap.get(object)).append(": ")
			  .append("<<create>> ").append(object.getClass().getSimpleName()).append('(');
			
			int paramNum = nextRegisterCreationParams.length;
			if (paramNum > 0) {
				for (int i = 0; i < paramNum - 1; i++)
					sb.append(getParameterName(nextRegisterCreationParams[i])).append(", ");
				sb.append(getParameterName(nextRegisterCreationParams[paramNum - 1]));
			}
			sb.append(')');
			
			System.out.println(sb.toString());
			
			nextRegisterCreator = nextRegisterCreationParams = null;
		}
	}
	
	public static void registerObject(Object object) {
		try {
			objectMap.put(object, registerNameStack.pop());
		} catch (EmptyStackException e) { System.err.println("A register name stack üres ! Feltehetőleg több nevet kéne rá tolni."); }
		
		if (nextRegisterCreator != null) {
			StringBuilder sb = new StringBuilder();
			
			
			sb.append(indentation).append(objectMap.get(nextRegisterCreator)).append("->").append(objectMap.get(object)).append(": ")
			  .append("<<create>> ").append(object.getClass().getSimpleName()).append('(');
			
			int paramNum = nextRegisterCreationParams.length;
			if (paramNum > 0) {
				for (int i = 0; i < paramNum - 1; i++)
					sb.append(getParameterName(nextRegisterCreationParams[i])).append(", ");
				sb.append(getParameterName(nextRegisterCreationParams[paramNum - 1]));
			}
			sb.append(')');
			
			System.out.println(sb.toString());
			
			nextRegisterCreator = nextRegisterCreationParams = null;
		}
	} 
	
	/**
	 * registerNameStackre tol neveket.
	 * A sorrend olyan, hogy a legelső paraméter lesz a stack tetején.
	 * 
	 * @param names A regisztrálandó nevek (vesszővel elválasztva)
	 */
	public static void pushToRegisterNameStack(String ...names) {
		int nameNum = names.length;
		
		for (int i = names.length - 1; i >= 0; i--)
			registerNameStack.push(names[i]);
	}
	
	/**
	 * Nevet ad az objektumnak, amit a Logger használ kiírásnál.
	 * 
	 * Ezt a nevet a registerNameStack tetejéről veszi ki.!
	 * 
	 * @param object Regisztrálandó objektum
	 * @param name A név, amit kapni fog (esetleg számozva, ha már van ilyen használva)
	 */
	
	
	
	/**
	 * Akkor hívandó, ha az egyik objektumunk egy másikat hoz létre. (az előtt)
	 * 
	 * A létrehozott objektum konstruktorja biztosan előbb fut le a registerObject-es regisztrációnál, ezért nem tudunk névvel hivatkozni rá azelőtt.
	 * Emiatt rábízzuk az objektum konstruktorjára a regisztrálást, mi itt csak azt mondjuk, hogy amikor regisztrálja magát, írodjon ki, hogy mi hoztuk létre, és ezeket a paramétereket adtuk neki (vagy semmit).
	 * @param creatorObject Az objektumot létrehozó objektum
	 * @param parameters A konstruktor paraméterei
	 */
	public static void createObjectNextRegister(Object creatorObject, Object ...parameters) {
		nextRegisterCreator = creatorObject;
		nextRegisterCreationParams = parameters;
	}
	
	
	/**
	 * Hívást irat ki caller-&gt;calledObject: calledMethodName([parameters]) formában.
	 * 
	 * @param caller Hívó objektum
	 * @param calledObject Hívandó objektum
	 * @param calledMethodName Használt metódus neve
	 * @param parameters Használt paraméterek (opcionális)
	 */
	public static void callObject(Object caller, Object calledObject, String calledMethodName, Object ...parameters) {
		String callerName = objectMap.get(caller);
		callerNameStack.add(callerName);
		
		StringBuilder sb = new StringBuilder();
		sb.append(indentation).append(callerName).append("->").append(objectMap.get(calledObject)).append(": ").append(calledMethodName).append('(');
		
		
		int paramNum = parameters.length;
		if (paramNum > 0) {
			for (int i = 0; i < paramNum - 1; paramNum++)
				sb.append(getParameterName(parameters[i])).append(", ");
			sb.append(getParameterName(parameters[paramNum - 1]));
		}
		sb.append(')');
		
		System.out.println(sb.toString());
		
		indentation = indentation + "\t";
	}
	
	/**
	 * returner objektum visszatérés egy hívásból retVal értékkel
	 * [indentation - 1] caller&lt;--returner: retVal formában kiírva.
	 * 
	 * @param returner Az objektum, aki visszatér a hívásból.
	 * @param retVal Visszatérési érték
	 */
	public static void returnFrom(Object returner, Object retVal) {
		// Hogy ne szálljon el, ha inicializálás közben használjuk
		if(callerNameStack.size() == 0) return;
		
		if (indentation.length() > 0)
			indentation = indentation.substring(0, indentation.length() - 1);

		StringBuilder sb = new StringBuilder();
		sb.append(indentation).append(callerNameStack.pop()).append("<--").append(objectMap.get(returner)).append(": ").append(getParameterName(retVal));
		
		System.out.println(sb.toString());
	}
	
	
	/**
	 * returner objektum visszatérés egy hívásból semmivel/voiddal
	 * [indentation - 1] caller&lt;--returner formában
	 * 
	 * @param returner Az objektum, aki visszatér a hívásból.
	 */
	public static void returnFrom(Object returner) {
		if (indentation.length() > 0)
			indentation = indentation.substring(0, indentation.length() - 1);
		
		StringBuilder sb = new StringBuilder();
		sb.append(indentation).append(callerNameStack.pop()).append("<--").append(objectMap.get(returner));
		
		System.out.println(sb.toString());
	}
	
	/**
	 * Az összes objektum regisztrálást elfelejti, hogy az egyik use case lefutása ne hasson a másikra.
	 */
	public static void clear() {
		indentation = "";
		objectMap.clear();
		nameUsageCounter.clear();
		nextRegisterCreator = nextRegisterCreationParams = null;
		callerNameStack.clear();
		registerNameStack.clear();
		
		objectMap.put(Game.INSTANCE, "Game");
	}

	private Logger(){}
}
