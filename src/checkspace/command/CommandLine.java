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
            listAvailableCommands();
            final Command command = readCommand();
            shouldContinue = command.execute(console);
        }
    }

    private Command readCommand()
    {
        final String line = console.readLine();
        for (Command command: commands)
        {
            if (command != null && command.accepts(line))
            {
                return command;
            }
        }
        return new UnknownCommand();
    }

    private void listAvailableCommands()
    {
        for (Command command: commands)
        {
            if (command != null)
            {
                console.printLine(command.getHelpLine());
            }
        }
    }
}
