package checkspace.commands;

import checkspace.gui.Console;

/** Termina a execução do aplicativo. **/
public class ExitCommand extends Command
{
    private static final String COMMAND = "s";

    private final Console console;

    public ExitCommand(Console console)
    {
        this.console = console;
    }

    @Override
    public String getHelpLine()
    {
        return COMMAND + ": Termina a execução do aplicativo.";
    }

    @Override
    public boolean accepts(String line)
    {
        return line.equals(COMMAND);
    }

    @Override
    public boolean execute(String line)
    {
        console.printLine("Terminando CheckSpace...");

        // Termina a aplicação.
        return false;
    }
}
