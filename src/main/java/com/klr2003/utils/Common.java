package com.klr2003.utils;

/** {@link Common} is for the common references that are always used and is easily changeable. */
public class Common {

  public static final String APPLICATION_TITLE = "The Boring App";
  public static final String CONSOLE_NAME = "theboringconsole";
  public static final String APPLICATION_VERSION = "0.0.1";

  public static final int DEFAULT_FRAME_WIDTH = 500;
  public static final int DEFAULT_FRAME_HEIGHT = 300;

  public static final String OS = System.getProperty("os.name");
  public static final String KEY = "v8x/A?K(G+KbPeSl";

  public static boolean isWindows() {
    return OS.contains("Windows");
  }
}
