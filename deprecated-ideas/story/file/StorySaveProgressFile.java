package com.klr2003.deprecated.story.file;

import com.klr2003.deprecated.story.StoryState;

@Deprecated
public class StorySaveProgressFile extends StoryProgressFile {

  public static StoryProgressFile storyProgressFile = new StoryProgressFile();

  public static void addProgress() {
    storyProgressFile.setChosenStory(StoryState.getChosenStory());
    storyProgressFile.setSavedState(StoryState.getStoryState());
  }
}
