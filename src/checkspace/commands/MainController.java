package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;
import javafx.event.ActionEvent;

public class MainController
{
  private final IO io;
  private final Command[] commands;

  public MainController()
  {
    this.io = new IO();

    final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(io);
    final UsageReport usageReport = new UsageReport(io);
    final RootFolderProcessingCommand rootFolderProcessingCommand =
      new RootFolderProcessingCommand(io, rootFolderProcessor, usageReport);

    this.commands = new Command[]
      {
        new ExitCommand(io),
        new ReportOrderColumnCommand(usageReport),
        new ReportOrderDirectionCommand(usageReport),
        rootFolderProcessingCommand
      };
  }

  private Command interpretCommandLine(String line)
  {
    for (Command command : commands)
    {
      if (command.accepts(line))
      {
        return command;
      }
    }
    return new UnknownCommand(io);
  }

  public void changeFolder(ActionEvent actionEvent)
  {
    System.out.println("Hello World!");
  }
}
