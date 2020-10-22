package com.klr2003.deprecated.story.anaesia;

import com.klr2003.deprecated.story.Story;
import com.klr2003.deprecated.story.StoryReferences;
import com.klr2003.deprecated.story.file.StoryCharacterFile;
import com.klr2003.deprecated.story.lang.LanguageMain;

@Deprecated
public class TheStoryOfAnaesia {

  public static LanguageMain lang;

  public static void startStory() {
    StoryCharacterFile.readCharacterFile();
    storyPrologue();
  }

  private static void inputParser() {}

  private static void storyPrologue() {
    Story.readStory(StoryReferences.story1, "Prologue");
  }

  private static void firstLevelChoices() {}

  private static void secondLevelChoices() {}

  private static void thirdLevelChoices() {}

  private static void fourthLevelChoices() {}

  private static void fifthLevelChoices() {}

  private static void sixthLevelChoices() {}

  private static void seventhLevelChoices() {}

  private static void eighthLevelChoices() {}

  private static void ninthLevelChoices() {}

  private static void tenthLevelChoices() {}

  private static void possibleEndings() {}
}
