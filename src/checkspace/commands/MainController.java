package checkspace.commands;

import checkspace.gui.MainWindow;

public class MainController
{
    private final MainWindow mainWindow;
    private final Command[] commands;

    public MainController(MainWindow mainWindow, Command[] commands)
    {
        this.mainWindow = mainWindow;
        this.commands = commands;
    }

    private void printAvailableCommands()
    {
        mainWindow.showMessage("Comandos disponíveis:");
        for (Command command: commands)
        {
            mainWindow.showMessage(command.getHelpLine());
        }
    }

    private void printPromptLine()
    {
        mainWindow.showMessage("\nDigite um comando and pressione ENTER para confirmar:");
    }

    private Command interpretCommandLine(String line)
    {
        for (Command command: commands)
        {
            if (command.accepts(line))
            {
                return command;
            }
        }
        return new UnknownCommand(mainWindow);
    }

}
