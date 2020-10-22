package com.klr2003.deprecated.story;

@Deprecated
public class StoryState {
  private static final Byte storyChoice = 0;
  private static final Byte firstLevelChoice = 0;
  private static final Byte secondLevelChoice = 0;
  private static final Byte thirdLevelChoice = 0;
  private static final Byte fourthLevelChoice = 0;
  private static final Byte fifthLevelChoice = 0;
  private static final Byte sixthLevelChoice = 0;
  private static final Byte seventhLevelChoice = 0;
  private static final Byte eighthLevelChoice = 0;
  private static final Byte ninthLevelChoice = 0;
  private static final Byte tenthLevelChoice = 0;

  public static Byte[] storyState = {
    firstLevelChoice,
    secondLevelChoice,
    thirdLevelChoice,
    fourthLevelChoice,
    fifthLevelChoice,
    sixthLevelChoice,
    seventhLevelChoice,
    eighthLevelChoice,
    ninthLevelChoice,
    tenthLevelChoice
  };

  public static Byte[] getStoryState() {
    return storyState;
  }

  public static Byte getChosenStory() {
    return storyChoice;
  }
}
