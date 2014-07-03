package checkspace.command;

import checkspace.console.Console;

public class CommandLine
{
    private final Console console;
    private final Command[] commands;

    public CommandLine(Console console, Command[] commands)
    {
        this.console = console;
        this.commands = commands;
    }

    public void executeCommands()
    {
        boolean shouldContinue = true;
        while (shouldContinue)
        {
            printAvailableCommands();
            printPromptLine();
            final Command command = readCommand();
            shouldContinue = command.execute(console.getLastLineRead());
        }
    }

    private void printAvailableCommands()
    {
        console.printLine("Comandos disponíveis:");
        for (Command command: commands)
        {
            console.printLine(command.getHelpLine());
        }
    }

    private void printPromptLine()
    {
        console.printLine("\nDigite um comando and pressione ENTER para confirmar:");
    }

    private Command readCommand()
    {
        final String line = console.readNewLine();
        for (Command command: commands)
        {
            if (command.accepts(line))
            {
                return command;
            }
        }
        return new UnknownCommand(console);
    }

}
