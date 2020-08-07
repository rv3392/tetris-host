package tech.richal.tetris.inputserver.command;

public class Command {
    private CommandType type;
    private String message;

    /**
     * Parses a command string and creates a new Command.
     * 
     * Command string should be of the format: <TYPE> <MESSAGE>
     * <TYPE> can be any of the valid types as represented in the
     * CommandType enum. While all Commands will accept a 
     * <MESSAGE> it is not guarranteed that anything is actually
     * done with this. It is up to the InputServerListener to
     * actually handle that.
     * 
     * @param commandString - Input command to be parsed
     * 
     * @throws IllegalArgumentException - if the commandString provided
     *      cannot be parsed into a type and a message
     */
    public Command(String commandString) throws IllegalArgumentException {
        if (commandString.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] commandParts = commandString.split(" ");

        String unparsedCommandType = commandParts[0];
        this.type = CommandType.valueOf(unparsedCommandType.toUpperCase());

        if (commandParts.length == 1) {
            this.message = "";
        } else if (commandParts.length == 2) {
            this.message = commandParts[1];
        } else if (commandParts.length > 2) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return The message of this command. If there is no message then
     *      an empty string ("") is returned.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return The type of this command.
     */
    public CommandType getType() {
        return type;
    }
}