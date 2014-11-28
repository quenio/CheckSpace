package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;
import javafx.beans.property.StringProperty;

import java.io.File;

public class RootFolderProcessingCommand
{
  private final IO io;
  private final StringProperty folderPathProperty;
  private final RootFolderProcessor rootFolderProcessor;
  private final UsageReport usageReport;

  public RootFolderProcessingCommand(
    IO io,
    StringProperty folderPathProperty,
    RootFolderProcessor rootFolderProcessor,
    UsageReport usageReport)
  {
    this.io = io;
    this.folderPathProperty = folderPathProperty;
    this.rootFolderProcessor = rootFolderProcessor;
    this.usageReport = usageReport;
  }

  public void initialize()
  {
    folderPathProperty.set(System.getProperty("user.home"));
  }

  public void execute()
  {
    final String folderPath = folderPathProperty.getValue();

    if (folderNotFound(folderPath))
    {
      io.showMessage("\nA pasta digitada não se encontra neste computador: %s", folderPath);
    }
    else
    {
      io.showMessage("\nAnalisando pasta raiz...");
      final RootFolder rootFolder = rootFolderProcessor.process(folderPath);
      usageReport.setRootFolder(rootFolder);
      usageReport.print();
    }
  }

  //  private String readRootFolderPath(IO io)
//  {
//    final String home = ;
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
