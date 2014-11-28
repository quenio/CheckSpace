package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;

import java.io.File;

public class RootFolderProcessingCommand
{
  private final IO io;
  private final RootFolderProcessor rootFolderProcessor;
  private final UsageReport usageReport;

  public RootFolderProcessingCommand(IO io, RootFolderProcessor rootFolderProcessor, UsageReport usageReport)
  {
    this.io = io;
    this.rootFolderProcessor = rootFolderProcessor;
    this.usageReport = usageReport;
  }

  public void execute(String path)
  {
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
  }

//  private String readRootFolderPath(IO io)
//  {
//    final String home = System.getProperty("user.home");
//    final String message = format("Digite o caminho da pasta a ser analisada (pressione ENTER para '%s'):", home);
//    final String line = io.askForInput(message);
//    final String path = line.equals("") ? home : line;
//
//    return line.startsWith("/") ? path : (home + File.separator + path);
//  }

  private boolean folderNotFound(String path)
  {
    return !new File(path).isDirectory();
  }
}
