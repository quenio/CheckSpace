package checkspace.app;

import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
import checkspace.gui.IO;
import checkspace.reports.UsageReport;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class MainController implements Controller
{
  private final IO io;
  private final StringProperty folderPathProperty = new SimpleStringProperty();
  private final UsageReport usageReport = new UsageReport(new IO());
  private final FolderAnalysisService folderAnalysisService = new FolderAnalysisService(folderPathProperty, usageReport);
  private final ReadOnlyStringProperty messageProperty = folderAnalysisService.messageProperty();

  @FXML
  private ResourceBundle resources;

  @FXML
  private TextField folderPathTextField;

  public MainController(IO io)
  {
    this.io = io;
  }

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
    folderPathProperty.setValue(System.getProperty("user.home"));
  }

  @FXML
  public void analyzeFolder()
  {
    folderAnalysisService.start();
  }

}
