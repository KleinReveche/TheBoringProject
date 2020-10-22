package com.klr2003.deprecated;

import com.klr2003.console.TheBoringConsole;
import com.klr2003.deprecated.story.StoryCharacter;
import com.klr2003.deprecated.story.StoryReferences;
import com.klr2003.deprecated.story.anaesia.TheStoryOfAnaesia;
import com.klr2003.console.handlers.ConsoleIOHandler;
import com.klr2003.utils.InternalConfiguration;

/*
This idea is scrapped due to difficulty in implementation.
 */

/**
 * This is a 'unique' addition to this program. It creates a story that is like a
 * Choose-Your-Own-Adventure (CYOA) that is in essence chosen by you. You choose the paths that are
 * pre-determined with varying outcomes.
 */
@Deprecated
public class ConsoleStory {

  public static String input;
  public static Byte storyNumber;
  public static Integer[] storyPath = new Integer[5];

  public static void startConsoleStory() {
    if (InternalConfiguration.isConsoleStoryEnabled) {
      if (TheBoringConsole.isStoryModeEnabled) {
        initStory();
      }
    } else {
      ConsoleIOHandler.printToConsole("'Stories' is not enabled by your configuration.");
      TheBoringConsole.isFizzBuzzConsoleEnabled = false;
      TheBoringConsole.isMainConsoleEnabled = true;
      TheBoringConsole.startConsole();
    }
  }

  private static void initStory() {
    chooseStory();
    while (TheBoringConsole.isStoryModeEnabled) {
      ConsoleIOHandler.systemPrinter("[Story] > ");
      input = ConsoleIOHandler.getConsoleStringInput();
      inputChecker();
    }
  }

  private static void chooseStory() {
    ConsoleIOHandler.printToConsole("Choose the title of the Story");
    ConsoleIOHandler.printToConsole("1) " + StoryReferences.story1Name);
    ConsoleIOHandler.printToConsole("2) ...........");
    // Add more
    ConsoleIOHandler.printToConsole("0) Continue Previous Storyline");
  }

  private static void inputChecker() {
    try {
      storyNumber = Byte.parseByte(input);
      selectStory();
    } catch (NumberFormatException e) {
      parseCommand();
    }
  }

  private static void selectStory() {
    switch (storyNumber) {
      case 0:
        ConsoleIOHandler.printToConsole("Retrieving Previous Story...");
        // Add code for retrieving previous story from array here
        break;
      case 1:
        ConsoleIOHandler.printToConsole("Selecting " + StoryReferences.story1Name);
        StoryCharacter.storyCharacter();
        ConsoleIOHandler.printToConsole("Starting Story...");
        TheStoryOfAnaesia.startStory();
        break;
      default:
        ConsoleIOHandler.printToConsole(
            "The Story you are trying to create is currently "
                + "being worked on or is simply not available at the moment");
    }
  }

  private static void parseCommand() {
    switch (input.toLowerCase()) {
      case "quit":
        ConsoleIOHandler.printToConsole("Exiting ConsoleStory...");
        TheBoringConsole.isStoryModeEnabled = false;
        TheBoringConsole.isMainConsoleEnabled = true;
        TheBoringConsole.startConsole();
        break;
      case "exit":
        System.exit(0);
        break;
      default:
        ConsoleIOHandler.printToConsole("Input a valid number or command");
    }
  }
}
