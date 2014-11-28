package checkspace.commands;

import checkspace.gui.MainWindow;

/** Termina a execução do aplicativo. **/
public class ExitCommand extends Command
{
    private static final String COMMAND = "s";

    private final MainWindow mainWindow;

    public ExitCommand(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
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
        mainWindow.showMessage("Terminando CheckSpace...");

        // Termina a aplicação.
        return false;
    }
}
