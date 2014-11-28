package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController
{
  private final IO io = new IO();
  private final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(io);
  private final UsageReport usageReport = new UsageReport(io);
  private final RootFolderProcessingCommand rootFolderProcessingCommand =
    new RootFolderProcessingCommand(io, rootFolderProcessor, usageReport);

  @FXML
  private TextField folderPathTextField;

  @FXML
  public void changeFolder()
  {
    final String folderPath = folderPathTextField.getText();
    rootFolderProcessingCommand.execute(folderPath);
  }
}
