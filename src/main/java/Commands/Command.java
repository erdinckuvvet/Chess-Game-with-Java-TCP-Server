package Commands;

/**
 * This class represents a communication object for sending serialized objects
 * to the server.
 */
public class Command implements java.io.Serializable {

    public static enum CommandTypes {
        MATCHED, START, MOVE, END, MATHCING, CHECK, LEAVE
    };

    public CommandTypes type;
    public Object content;

    public Command(CommandTypes type) {
        this.type = type;
    }
}
