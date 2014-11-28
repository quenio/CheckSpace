package checkspace.commands;

import checkspace.gui.Console;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderProcessor;
import checkspace.report.UsageReport;

import java.io.File;

import static java.lang.String.format;

public class RootFolderProcessingCommand extends Command
{
    private static final String COMMAND = "p";

    private final Console console;
    private final RootFolderProcessor rootFolderProcessor;
    private final UsageReport usageReport;

    public RootFolderProcessingCommand(Console console, RootFolderProcessor rootFolderProcessor, UsageReport usageReport)
    {
        this.console = console;
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
    public boolean execute(String line)
    {
        return execute();
    }

    public boolean execute()
    {
        final String path = readRootFolderPath(console);
        if (folderNotFound(path))
        {
            console.printLine("\nA pasta digitada não se encontra neste computador: ", path);
        }
        else
        {
            console.printLine("\nAnalisando pasta raiz...");
            final RootFolder rootFolder = rootFolderProcessor.process(path);
            usageReport.setRootFolder(rootFolder);
            usageReport.print();
        }

        // Permite a execução de outros comandos após esse.
        return true;
    }

    private String readRootFolderPath(Console console)
    {
        final String home = System.getProperty("user.home");
        console.printLine(format("Digite o caminho da pasta a ser analisada (pressione ENTER para '%s'):", home));

        final String line = console.readNewLine();
        final String path = line.equals("") ? home : line;

        return line.startsWith("/") ? path : (home + File.separator + path);
    }

    private boolean folderNotFound(String path)
    {
        return !new File(path).isDirectory();
    }
}
