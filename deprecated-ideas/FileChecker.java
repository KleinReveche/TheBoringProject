package com.klr2003.utils.file;

import com.klr2003.console.handlers.ConsoleIOHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileChecker {

  public static void checkFile(File file, String name) {

    file = new File(name);
    if (!file.exists()) {
      try {
        new FileWriter(name);
      } catch (IOException e) {
        ConsoleIOHandler.printToConsole("There seems to be a problem creating the file.");
        e.printStackTrace();
      }
    }
  }
}
