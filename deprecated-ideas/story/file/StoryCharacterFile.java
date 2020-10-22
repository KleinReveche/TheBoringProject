package com.klr2003.deprecated.story.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.klr2003.deprecated.story.StoryCharacter;
import com.klr2003.console.handlers.ConsoleIOHandler;
import com.klr2003.utils.CommonReferences;
import com.klr2003.utils.file.cipher.CipherException;
import com.klr2003.utils.file.cipher.CipherUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Deprecated
public class StoryCharacterFile {

  private static final Gson gson = new Gson();
  private static final String CHARACTER_FILE_NAME = "character";
  private static final String FILE_EXT = ".smf";
  private static final String STORY_CHARACTER_FILE_NAME = CHARACTER_FILE_NAME + FILE_EXT;
  public static String savedCharacterFileLocation;
  private String firstName;
  private String lastName;
  private String fullName;
  private String eyeColor;
  private String gender;

  public static void checkForOS() {
    if (Common.isWindows()) {
      savedCharacterFileLocation =
          "%AppData%/" + CommonReferences.CONSOLE_NAME + STORY_CHARACTER_FILE_NAME;
    } else {
      savedCharacterFileLocation = "" + STORY_CHARACTER_FILE_NAME;
    }
  }

  public static void saveCharacterFile() {
    ConsoleIOHandler.printToConsole("Saving Character..");
    String firstName = StoryCharacter.characterFirstName;
    String lastName = StoryCharacter.characterLastName;
    String eyeColor = StoryCharacter.characterEyeColour;
    String gender = StoryCharacter.characterGender;

    StoryCharacterFile character = new StoryCharacterFile();
    character.setFirstName(firstName);
    character.setLastName(lastName);
    character.setEyeColor(eyeColor);
    character.setGender(gender);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(gson.toJson(character));
    String prettyJsonString = gson.toJson(je);
    writeCharacterToFile(prettyJsonString);
  }

  private static void writeCharacterToFile(String savedStoryProgressData) {
    checkForOS();
    File savedTempStoryFile = null;
    try {
      savedTempStoryFile = File.createTempFile(CHARACTER_FILE_NAME, FILE_EXT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (!savedTempStoryFile.exists()) {
      try {
        File directory = new File(savedTempStoryFile.getParent());
        if (!directory.exists()) {
          directory.mkdirs();
        }
        savedTempStoryFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      FileWriter savedProgressWriter;
      savedProgressWriter = new FileWriter(savedTempStoryFile.getAbsoluteFile(), true);

      BufferedWriter bufferWriter = new BufferedWriter(savedProgressWriter);
      bufferWriter.write(savedStoryProgressData);
      bufferWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    File savedStoryProgressFile = new File(savedCharacterFileLocation);
    if (!savedStoryProgressFile.exists()) {
      try {
        savedStoryProgressFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      CipherUtils.encrypt(Common.KEY, savedTempStoryFile, savedStoryProgressFile);
    } catch (CipherException e) {
      ConsoleIOHandler.printToConsole("There was a problem in saving the story...");
      e.printStackTrace();
    }
  }

  public static void readCharacterFile() {
    checkForOS();
    File storyProgressFile = new File(savedCharacterFileLocation);
    if (!storyProgressFile.exists())
      ConsoleIOHandler.printToConsole("File does not exist");
    File savedTempStoryProgressFile = null;
    try {
      savedTempStoryProgressFile = File.createTempFile(CHARACTER_FILE_NAME, FILE_EXT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (!savedTempStoryProgressFile.exists()) {
      try {
        File directory = new File(savedTempStoryProgressFile.getParent());
        if (!directory.exists()) {
          directory.mkdirs();
        }
        savedTempStoryProgressFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      CipherUtils.decrypt(Common.KEY, storyProgressFile, savedTempStoryProgressFile);
    } catch (CipherException e) {
      e.printStackTrace();
    }
    InputStreamReader inputStreamReader;
    try {
      inputStreamReader =
          new InputStreamReader(
              new FileInputStream(savedTempStoryProgressFile), StandardCharsets.UTF_8);

      JsonReader jsonReader = new JsonReader(inputStreamReader);
      StoryCharacterFile storyCharacterFile = gson.fromJson(jsonReader, StoryCharacterFile.class);

      StoryCharacter.characterFirstName = storyCharacterFile.getFirstName();
      StoryCharacter.characterLastName = storyCharacterFile.getLastName();
      StoryCharacter.characterFullName =
          storyCharacterFile.getFirstName() + " " + storyCharacterFile.getLastName();
      StoryCharacter.characterGender = storyCharacterFile.getGender();
      StoryCharacter.characterEyeColour = storyCharacterFile.getEyeColour();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void setEyeColor(String eyeColor) {
    this.eyeColor = eyeColor;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    getFirstName();
    getLastName();
    return getFirstName() + " " + getLastName();
  }

  public String getEyeColour() {
    return this.eyeColor;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
