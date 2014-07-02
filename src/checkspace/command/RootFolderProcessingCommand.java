package checkspace.command;

import checkspace.console.Console;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderProcessor;
import checkspace.report.UsageReport;

import java.io.File;

import static java.lang.String.format;

public class RootFolderProcessingCommand implements Command
{
    private static final String COMMAND = "p";

    private final RootFolderProcessor rootFolderProcessor;
    private final UsageReport usageReport;

    public RootFolderProcessingCommand(RootFolderProcessor rootFolderProcessor, UsageReport usageReport)
    {
        this.rootFolderProcessor = rootFolderProcessor;
        this.usageReport = usageReport;
    }

    @Override
    public String getHelpLine()
    {
        return COMMAND + ": Faz análise em outra pasta.";
    }

    @Override
    public boolean accepts(String line)
    {
        return line.equals(COMMAND);
    }

    @Override
    public boolean execute(Console console)
    {
        final String path = readRootFolderPath(console);
        if (folderNotFound(path))
        {
            console.printLine("A pasta digitada não se encontra neste computador.");
        }
        else
        {
            console.printLine("Analisando pasta...");
            final RootFolder rootFolder = rootFolderProcessor.process(path);
            usageReport.print(rootFolder);
        }

        // Permite a execução de outros comandos após esse.
        return true;
    }

    private String readRootFolderPath(Console console)
    {
        final String home = System.getProperty("user.home");
        console.printLine(format("Digite o caminho da pasta a ser analisada (pressione ENTER para '%s'):", home));

        final String line = console.readLine();

        return line.trim().equals("") ? home : line;
    }

    private boolean folderNotFound(String path)
    {
        return !new File(path).isDirectory();
    }
}
