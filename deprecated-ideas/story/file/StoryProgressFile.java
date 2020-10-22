package com.klr2003.deprecated.story.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.klr2003.deprecated.story.StoryProgress;
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
public class StoryProgressFile {

  private static final Gson gson = new Gson();
  private static final String PROGRESS_FILE_NAME = "progress";
  private static final String CHARACTER_FILE_NAME = "character";
  private static final String FILE_EXT = ".smf";
  private static final String STORY_PROGRESS_FILE_NAME = PROGRESS_FILE_NAME + FILE_EXT;
  private static String savedStoryProgressFileLocation;
  private Byte[] savedState;
  private Byte chosenStory;

  public static void checkForOS() {
    if (Common.isWindows()) {
      savedStoryProgressFileLocation =
          "%AppData%/" + CommonReferences.CONSOLE_NAME + STORY_PROGRESS_FILE_NAME;
    } else {
      savedStoryProgressFileLocation = "" + STORY_PROGRESS_FILE_NAME;
    }
  }

  public static void saveStoryProgress() {
    ConsoleIOHandler.printToConsole("Saving Story Progress...");
    Byte[] savedState = StoryProgress.storyState;
    Byte chosenStory = StoryProgress.storyChoice;

    StoryProgressFile progress = new StoryProgressFile();
    progress.setSavedState(savedState);
    progress.setChosenStory(chosenStory);
    // Prettifier Code for JSON.
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(gson.toJson(progress));
    String prettyJsonString = gson.toJson(je);
    writeStoryProgressToFile(prettyJsonString);
  }

  private static void writeSavedStoryToFile() {
    // For Printing the Story
  }

  private static void writeStoryProgressToFile(String savedStoryProgressData) {
    checkForOS();
    File savedTempStoryProgressFile = null;
    try {
      savedTempStoryProgressFile = File.createTempFile(PROGRESS_FILE_NAME, FILE_EXT);
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
      FileWriter savedProgressWriter;
      savedProgressWriter = new FileWriter(savedTempStoryProgressFile.getAbsoluteFile(), true);

      BufferedWriter bufferWriter = new BufferedWriter(savedProgressWriter);
      bufferWriter.write(savedStoryProgressData);
      bufferWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    File savedStoryProgressFile = new File(savedStoryProgressFileLocation);
    if (!savedStoryProgressFile.exists()) {
      try {
        savedStoryProgressFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      CipherUtils.encrypt(Common.KEY, savedTempStoryProgressFile, savedStoryProgressFile);
    } catch (CipherException e) {
      ConsoleIOHandler.printToConsole("There was a problem in saving the story...");
      e.printStackTrace();
    }
  }

  public static void readStoryProgressFile() {
    checkForOS();
    File storyProgressFile = new File(savedStoryProgressFileLocation);
    if (!storyProgressFile.exists())
      ConsoleIOHandler.printToConsole("File does not exist");
    File savedTempStoryProgressFile = null;
    try {
      savedTempStoryProgressFile = File.createTempFile(PROGRESS_FILE_NAME, FILE_EXT);
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
      StoryProgressFile storySavedFile = gson.fromJson(jsonReader, StoryProgressFile.class);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Byte[] getSavedState() {
    return savedState;
  }

  public void setSavedState(Byte[] savedStateData) {
    savedState = savedStateData;
  }

  public Byte getChosenStory() {
    return chosenStory;
  }

  public void setChosenStory(Byte chosenStory) {
    this.chosenStory = chosenStory;
  }
}
