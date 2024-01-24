package g60131.atlg3.ascii.util;

public interface Command {
    /**
     * Execute a command
     */
    void execute();

    /**
     * Unexecute a command
     */
    void unexecute();
}