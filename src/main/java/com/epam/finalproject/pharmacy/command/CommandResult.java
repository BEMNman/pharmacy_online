package com.epam.finalproject.pharmacy.command;

public class CommandResult {

    private static final String PATH_REDIRECT_COMMAND = "controller?command=";

    private final String page;
    private final boolean isRedirect;

    private CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public static CommandResult redirectToCommand(String page) {
        String command = PATH_REDIRECT_COMMAND + page;
        return new CommandResult(command, true);
    }

    public static CommandResult redirectToCommandWithParam(String page, String param, Object value) {
        String command = PATH_REDIRECT_COMMAND + page + "&" + param + "=" + value;
        return new CommandResult(command, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
