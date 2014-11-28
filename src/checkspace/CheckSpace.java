package checkspace;

import checkspace.commands.*;
import checkspace.gui.MainWindow;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;

public class CheckSpace
{
  public static void main(String[] args)
  {
    // Instanciação das classes do aplicativo:
    final MainWindow mainWindow = new MainWindow();
    final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(mainWindow);
    final UsageReport usageReport = new UsageReport(mainWindow);
    final RootFolderProcessingCommand rootFolderProcessingCommand =
      new RootFolderProcessingCommand(mainWindow, rootFolderProcessor, usageReport);
    final MainController mainController = new MainController(
      mainWindow,
      new Command[]
        {
          new ExitCommand(mainWindow),
          new ReportOrderColumnCommand(usageReport),
          new ReportOrderDirectionCommand(usageReport),
          rootFolderProcessingCommand
        });

    // Apresentação ao usuário:
    mainWindow.show();
    mainWindow.showMessage("CheckSpace - auxilia na liberação de espaço em seu computador...\n");

    // Começa pedindo ao usuário que escolha uma pasta-raiz:
    rootFolderProcessingCommand.execute();
  }
}
