package com.klr2003;

import com.klr2003.console.Debug;
import com.klr2003.console.TheBoringConsole;
import com.klr2003.utils.InternalConfiguration;

public class TheBoringApp {

  public static void main(String[] args) {
    if (InternalConfiguration.isDebugModeEnabled) {
      Debug.startDebug();
      System.out.println("Initializing Program...");
    }
    //Initializes the Console if enabled.
    if (InternalConfiguration.isConsoleModeEnabled) TheBoringConsole.startConsole();
  }
}
