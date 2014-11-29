package checkspace.app;

import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController implements Controller
{
  private final FolderAnalysisService folderAnalysisService;

  @FXML
  private TextField folderPathTextField;

  public MainController(FolderAnalysisService folderAnalysisService)
  {
    this.folderAnalysisService = folderAnalysisService;
  }

  @FXML
  public String getMessage()
  {
    return messageProperty().get();
  }

  @FXML
  public ReadOnlyStringProperty messageProperty()
  {
    return folderAnalysisService.messageProperty();
  }

  @FXML
  public void initialize()
  {
    folderAnalysisService.folderPathProperty().bind(folderPathTextField.textProperty());
    folderPathTextField.setText(System.getProperty("user.home"));
  }

  @FXML
  public void analyzeFolder()
  {
    folderAnalysisService.start();
  }

}
