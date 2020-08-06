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
     */
    public Command(String commandString) {
        String[] commandParts = commandString.split(" ");

        String unparsedCommandType = commandParts[0];
        this.type = CommandType.valueOf(unparsedCommandType.toUpperCase());

        this.message = commandParts[1];
    }

    /**
     * @return the message of this command
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the type of this command
     */
    public CommandType getType() {
        return type;
    }
}