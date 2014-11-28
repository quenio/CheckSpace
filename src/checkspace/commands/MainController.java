package checkspace.commands;

import checkspace.gui.IO;
import checkspace.processing.RootFolderProcessor;
import checkspace.reports.UsageReport;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController
{
  private final IO io = new IO();
  private final StringProperty folderPathProperty = new SimpleStringProperty();
  private final RootFolderProcessor rootFolderProcessor = new RootFolderProcessor(io);
  private final UsageReport usageReport = new UsageReport(io);
  private final RootFolderProcessingCommand rootFolderProcessingCommand =
    new RootFolderProcessingCommand(io, folderPathProperty, rootFolderProcessor, usageReport);

  @FXML
  private TextField folderPathTextField;

  @FXML
  public void initialize()
  {
    folderPathProperty.bindBidirectional(folderPathTextField.textProperty());
    rootFolderProcessingCommand.initialize();
  }

  @FXML
  public void changeFolder()
  {
    rootFolderProcessingCommand.execute();
  }
}
