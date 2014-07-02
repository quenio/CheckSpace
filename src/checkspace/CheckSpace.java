package checkspace;

import checkspace.command.Command;
import checkspace.command.CommandLine;
import checkspace.command.ExitCommand;
import checkspace.command.RootFolderProcessingCommand;
import checkspace.console.Console;
import checkspace.processing.RootFolderProcessor;
import checkspace.report.UsageReport;

public class CheckSpace
{
    public static void main(String[] args)
    {
        final Console console = new Console();
        final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(console);
        final UsageReport usageReport = new UsageReport(console);
        final RootFolderProcessingCommand rootFolderProcessingCommand =
                new RootFolderProcessingCommand(rootFolderProcessor, usageReport);
        final CommandLine commandLine = new CommandLine(
                console,
                new Command[]
                {
                    new ExitCommand(),
                    rootFolderProcessingCommand
                });

        // Introdução:
        console.printLine("CheckSpace - auxilia na liberação de espaço em seu computador...\n");

        // Começa pedindo ao usuário que escolha uma pasta-raíz:
        rootFolderProcessingCommand.execute(console);

        // Depois permite executar outros comandos:
        commandLine.executeCommands();
    }
}
