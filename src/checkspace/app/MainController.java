package checkspace.app;

import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.IO;
import checkspace.reports.UsageReport;
import javafx.beans.property.ReadOnlyStringProperty;
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
  private final ReadOnlyStringProperty messageProperty = folderAnalysisService.messageProperty();

  @FXML
  private TextField folderPathTextField;

  @FXML
  public String getMessage()
  {
    return messageProperty.get();
  }

  @FXML
  public ReadOnlyStringProperty messageProperty()
  {
    return messageProperty;
  }

  @FXML
  public void initialize()
  {
    folderPathProperty.bindBidirectional(folderPathTextField.textProperty());
    folderPathProperty.set(System.getProperty("user.home"));

  }

  @FXML
  public void analyzeFolder()
  {
    folderAnalysisService.start();
  }
}
