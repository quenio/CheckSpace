package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;

import java.io.File;

import static java.lang.String.format;

public class RootFolderProcessingCommand extends Command
{
  private static final String COMMAND = "p";

  private final IO io;
  private final RootFolderProcessor rootFolderProcessor;
  private final UsageReport usageReport;

  public RootFolderProcessingCommand(IO io, RootFolderProcessor rootFolderProcessor, UsageReport usageReport)
  {
    this.io = io;
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
    final String path = readRootFolderPath(io);
    if (folderNotFound(path))
    {
      io.showMessage("\nA pasta digitada não se encontra neste computador: ", path);
    }
    else
    {
      io.showMessage("\nAnalisando pasta raiz...");
      final RootFolder rootFolder = rootFolderProcessor.process(path);
      usageReport.setRootFolder(rootFolder);
      usageReport.print();
    }

    // Permite a execução de outros comandos após esse.
    return true;
  }

  private String readRootFolderPath(IO io)
  {
    final String home = System.getProperty("user.home");
    final String message = format("Digite o caminho da pasta a ser analisada (pressione ENTER para '%s'):", home);
    final String line = io.askForInput(message);
    final String path = line.equals("") ? home : line;

    return line.startsWith("/") ? path : (home + File.separator + path);
  }

  private boolean folderNotFound(String path)
  {
    return !new File(path).isDirectory();
  }
}
