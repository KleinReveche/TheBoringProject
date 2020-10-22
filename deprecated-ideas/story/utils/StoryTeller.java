package com.klr2003.deprecated.story.utils;

import com.klr2003.console.handlers.ConsoleIOHandler;

@Deprecated
public class StoryTeller {

  public static void tellPrologue(String storyName, String prologue) {
    ConsoleIOHandler.printToConsole(storyName);
    ConsoleIOHandler.printToConsole("-Prologue");
    ConsoleIOHandler.printToConsole(prologue);
  }

  public static void tellChapter(int chapter, String body) {
    ConsoleIOHandler.printToConsole("Chapter " + chapter);
    ConsoleIOHandler.printToConsole(body);
  }

  public static void tellEnding(String ending) {
    ConsoleIOHandler.printToConsole(ending);
  }
}
