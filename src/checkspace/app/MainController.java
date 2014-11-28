package checkspace.app;

import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.IO;
import checkspace.reports.UsageReport;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController
{
  private final IO io = new IO();
  private final StringProperty folderPathProperty = new SimpleStringProperty();
  private final UsageReport usageReport = new UsageReport(io);
  private final FolderAnalysisService folderAnalysisService = new FolderAnalysisService(folderPathProperty, usageReport);

  @FXML
  private TextField folderPathTextField;

  @FXML
  public void initialize()
  {
    folderPathProperty.bindBidirectional(folderPathTextField.textProperty());
    folderPathProperty.set(System.getProperty("user.home"));
  }

  @FXML
  public void changeFolder()
  {
    folderAnalysisService.start();
  }
}
