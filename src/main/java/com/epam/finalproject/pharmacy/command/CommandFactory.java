package com.epam.finalproject.pharmacy.command;

public class CommandFactory {
    public static Command create(String action) {
        Command command = null;
        switch (action) {
            case "login":
                command = new LoginCommand();
                break;
            case "oder":
                command = null;
        }
        return command;
    }
}
