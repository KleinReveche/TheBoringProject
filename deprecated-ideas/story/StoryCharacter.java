package com.klr2003.deprecated.story;

import com.klr2003.deprecated.story.file.StoryCharacterFile;
import com.klr2003.deprecated.story.file.StoryProgressFile;
import com.klr2003.console.handlers.ConsoleIOHandler;
import java.io.File;

@Deprecated
public class StoryCharacter extends StoryCharacterFile {

  public static String characterFirstName = "";
  public static String characterLastName = "";
  public static String characterFullName = "";
  public static String characterEyeColour = "";
  public static String characterGender = "";

  public static void storyCharacter() {
    boolean createCharacter = true;
    checkForOS();
    File characterFile = new File(savedCharacterFileLocation);
    if (characterFile.exists()) {
      boolean isActionTaken = false;
      while (!isActionTaken) {
        ConsoleIOHandler.printToConsole("Choose action for your Character");
        ConsoleIOHandler.printToConsole("1) Create a new Character");
        ConsoleIOHandler.printToConsole("2) Use Existing Character");
        ConsoleIOHandler.systemPrinter("[Character] > ");
        String action = ConsoleIOHandler.getConsoleStringInput();
        switch (action) {
          case "1":
            isActionTaken = true;
            break;
          case "2":
            isActionTaken = true;
            createCharacter = false;
            break;
          default:
            ConsoleIOHandler.printToConsole("Invalid Choice");
        }
        if (createCharacter) createCharacter();
        else StoryCharacterFile.readCharacterFile();
      }
    } else createCharacter();
  }

  public static void createCharacter() {
    ConsoleIOHandler.systemPrinter("[First Name] > ");
    characterFirstName = ConsoleIOHandler.getConsoleStringInput();
    ConsoleIOHandler.systemPrinter("[Last Name] > ");
    characterLastName = ConsoleIOHandler.getConsoleStringInput();
    ConsoleIOHandler.systemPrinter("[Eye Colour] > ");
    characterEyeColour = ConsoleIOHandler.getConsoleStringInput();
    boolean isGenderSelected = false;
    while (!isGenderSelected) {
      ConsoleIOHandler.systemPrinter("[Gender] > ");
      String gender = ConsoleIOHandler.getConsoleStringInput();
      switch (gender.toLowerCase()) {
        case "male":
        case "female":
          characterGender = gender.toLowerCase();
          isGenderSelected = true;
          break;
        case "boy":
          characterGender = "MALE";
          isGenderSelected = true;
          break;
        case "girl":
          characterGender = "FEMALE";
          isGenderSelected = true;
          break;
        default:
          ConsoleIOHandler.printToConsole("Male and Female values are only allowed");
      }
    }
    characterFullName = characterFirstName + " " + characterLastName;

    ConsoleIOHandler.printToConsole("Your Character Details:");
    ConsoleIOHandler.printToConsole(
        "Your character name is "
            + characterFullName
            + ". You have "
            + characterEyeColour
            + " coloured eyes. Your gender is "
            + characterGender);
    ConsoleIOHandler.printToConsole("Are you sure?");
    checkIfSatisfied();
    StoryCharacterFile.saveCharacterFile();
    StoryProgressFile.saveStoryProgress();
  }

  private static void checkIfSatisfied() {
    boolean isSatisfied = false;
    while (!isSatisfied) {
      ConsoleIOHandler.systemPrinter("[Character] > ");
      String satisfied = ConsoleIOHandler.getConsoleStringInput();
      switch (satisfied.toLowerCase()) {
        case "yes":
        case "y":
          isSatisfied = true;
          break;
        case "no":
        case "n":
          isSatisfied = false;
          break;
        default:
          ConsoleIOHandler.printToConsole("Yes or No");
      }
      if (!isSatisfied) createCharacter();
    }
  }
}
