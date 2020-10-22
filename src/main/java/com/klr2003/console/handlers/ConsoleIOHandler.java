package com.klr2003.console.handlers;

import java.util.Scanner;

public class ConsoleIOHandler {

  private static final Scanner scanner = new Scanner(System.in);

  /**
   * Acquires the user input from Console
   *
   * @return Returns the Input
   */
  public static String getConsoleStringInput() {
    return scanner.nextLine();
  }

  /**
   * This simplifies the System.out.println()
   *
   * @param text Message to Print to Console
   */
  public static void printToConsole(String text) {
    System.out.println(text);
  }

  /**
   * This also simplifies System.out.println() but with a name prefix.
   *
   * @param name Name Prefix for the Message
   * @param text Message to Print to Console
   */
  public static void printToConsole(String name, String text) {
    System.out.println(name + " " + text);
  }

  /**
   * Print String to Console without a new line.
   *
   * @param text Message to Print to Console
   */
  public static void systemPrinter(String text) {
    System.out.print(text);
  }
}
