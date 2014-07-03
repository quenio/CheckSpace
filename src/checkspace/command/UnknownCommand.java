package checkspace.command;

import checkspace.console.Console;

public class UnknownCommand extends Command
{
    private final Console console;

    public UnknownCommand(Console console)
    {
        this.console = console;
    }

    /** Nunca deve ser chamado nesta classe. **/
    @Override
    public String getHelpLine()
    {
        throw new UnsupportedOperationException("Nunca deve ser chamado nesta classe.");
    }

    /** Nunca deve ser chamado nesta classe. **/
    @Override
    public boolean accepts(String line)
    {
        throw new UnsupportedOperationException("Nunca deve ser chamado nesta classe.");
    }

    @Override
    public boolean execute(String line)
    {
        console.printLine("\nO comando digitado não foi reconhecido. Por favor, tente novamente.\n");

        // Continua execução para que o usuário possa tentar novamente.
        return true;
    }
}
