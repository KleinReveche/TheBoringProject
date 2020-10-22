package com.klr2003.console.classic;

import com.klr2003.console.handlers.ConsoleIOHandler;
import com.klr2003.utils.InternalConfiguration;

import static com.klr2003.console.TheBoringConsole.*;

/**
 * {@link FizzBuzz} This is a classic Computer Science Question based on the game FizzBuzz where you
 * count numbers unless you hit a number divisible by 3 then you say Fizz. When you hit a number
 * that is divisible by 5 you say Buzz. If the number is both divisible by 3 ad 5 then you say
 * FizzBuzz.
 */
public class FizzBuzz {

  private static final int FIZZ_INT = 3;
  private static final int BUZZ_INT = 5;
  private static long input = 0;
  private static String inputString;
  private static String output = "";

  public FizzBuzz(){
    
  }

  /** Start the children's game: FizzBuzz */
  public static void startFizzBuzz() {

    if (InternalConfiguration.isFizzBuzzEnabled) {
      if (isFizzBuzzConsoleEnabled) initFizzBuzz();
    } else {
      ConsoleIOHandler.printToConsole("FizzBuzz is not enabled by your configuration.");
      isFizzBuzzConsoleEnabled = false;
      isMainConsoleEnabled = true;
      startConsole();
    }
  }

  private static void initFizzBuzz() {
    while (true) {
      if (isFizzBuzzConsoleEnabled) {
        ConsoleIOHandler.systemPrinter("[FizzBuzz] > ");
        inputString = ConsoleIOHandler.getConsoleStringInput();
        try {
          input = Integer.parseInt(inputString);
          fizzBuzzEngine();
        } catch (NumberFormatException e) {
          if (inputString.equalsIgnoreCase("exit")) System.exit(0);
          if (inputString.equalsIgnoreCase("quit")) {
            ConsoleIOHandler.printToConsole("Exiting FizzBuzz...");
            isFizzBuzzConsoleEnabled = false;
            isMainConsoleEnabled = true;
            startConsole();
          }
          asteriskParser();
        }
        continue;
      }
      break;
    }
  }

  private static void fizzBuzzEngine() {
    if (input % FIZZ_INT == 0) output += "Fizz";
    if (input % BUZZ_INT == 0) output += "Buzz";

    if (output.equals("")) output = Long.toString(input);
    ConsoleIOHandler.printToConsole(output);
    output = "";
  }

  private static void asteriskParser() {
    if (inputString.endsWith("*")) {
      inputString = inputString.substring(0, inputString.length() - 1);
      int endNumber = Integer.parseInt(inputString);
      for (int i = 1; i <= endNumber; i++) {
        input = i;
        fizzBuzzEngine();
      }
    }
  }
}
