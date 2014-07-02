package checkspace.command;

import checkspace.console.Console;

/** Termina a execução do aplicativo. **/
public class ExitCommand implements Command
{
    private static final String COMMAND = "s";

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
    public boolean execute(Console console)
    {
        console.printLine("Terminando CheckSpace...");

        // Termina a aplicação.
        return false;
    }
}
