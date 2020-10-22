package com.klr2003.console;

import com.klr2003.console.handlers.ConsoleIOHandler;
import com.klr2003.utils.Common;

public class Debug {
    public static void startDebug(){
        ConsoleIOHandler.printToConsole("*DEBUG MODE ENABLED*");
        inquireSystemDetails();
    }

    private static void inquireSystemDetails(){
        ConsoleIOHandler.printToConsole("|=======================================|");
        ConsoleIOHandler.printToConsole("| [Debug] System Details");
        ConsoleIOHandler.printToConsole("| Operating System: " + Common.OS);
        ConsoleIOHandler.printToConsole("| Java Version: " + System.getProperty("java.version"));
        ConsoleIOHandler.printToConsole("|=======================================|");
        ConsoleIOHandler.printToConsole("");
    }
}
