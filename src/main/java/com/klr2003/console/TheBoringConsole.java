package com.klr2003.console;

import com.klr2003.console.calculator.MortgageCalculator;
import com.klr2003.console.classic.FizzBuzz;
import com.klr2003.console.handlers.ConsoleIOHandler;
import com.klr2003.utils.Common;

public class TheBoringConsole {

  public static String input;

  public static boolean isStartMessageDone = false;
  public static boolean isMainConsoleEnabled = true;
  public static boolean isFizzBuzzConsoleEnabled = false;

  /** Starts the Console * */
  public static void startConsole() {

    if (!isStartMessageDone) {
      ConsoleIOHandler.printToConsole("Welcome to " + Common.APPLICATION_TITLE + "!");
      ConsoleIOHandler.printToConsole("");
    }
    isStartMessageDone = true;
    while (isMainConsoleEnabled) {
      ConsoleIOHandler.systemPrinter("> ");
      input = ConsoleIOHandler.getConsoleStringInput();
      inputChecker();
    }
  }

  /** Checks the Input against pre-defined values */
  private static void inputChecker() {
    switch (input.toLowerCase()) {
      case "fizzbuzz":
        isMainConsoleEnabled = false;
        isFizzBuzzConsoleEnabled = true;
        FizzBuzz.startFizzBuzz();
        break;
      case "exit":
        System.exit(0);
        break;
      case "mortgage" :
        new MortgageCalculator();
        break;
      case "help":
        printHelp();
        break;
      default:
        ConsoleIOHandler.printToConsole("Command not Found");
    }
  }

  private static void printHelp() {
    ConsoleIOHandler.printToConsole(Common.APPLICATION_TITLE + ": " + Common.APPLICATION_VERSION);
    ConsoleIOHandler.printToConsole("    FizzBuzz - Try the FizzBuzz Game in Console.");
    ConsoleIOHandler.printToConsole("    exit - Exit the Program");
  }
}
