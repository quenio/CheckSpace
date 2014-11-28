package checkspace;

import checkspace.commands.*;
import checkspace.gui.Console;
import checkspace.processing.RootFolderProcessor;
import checkspace.report.UsageReport;

public class CheckSpace
{
    public static void main(String[] args)
    {
        // Instanciação das classes do aplicativo:
        final Console console = new Console();
        final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(console);
        final UsageReport usageReport = new UsageReport(console);
        final RootFolderProcessingCommand rootFolderProcessingCommand =
                new RootFolderProcessingCommand(console, rootFolderProcessor, usageReport);
        final CommandLine commandLine = new CommandLine(
                console,
                new Command[]
                {
                    new ExitCommand(console),
                    new ReportOrderColumnCommand(usageReport),
                    new ReportOrderDirectionCommand(usageReport),
                    rootFolderProcessingCommand
                });

        // Apresentação ao usuário:
        console.printLine("CheckSpace - auxilia na liberação de espaço em seu computador...\n");

        // Começa pedindo ao usuário que escolha uma pasta-raiz:
        rootFolderProcessingCommand.execute();

        // Depois permite executar outros comandos:
        commandLine.executeCommands();
    }
}
