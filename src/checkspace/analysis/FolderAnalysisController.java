package checkspace.analysis;

import checkspace.gui.Controller;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FolderAnalysisController extends Controller implements EventHandler<WorkerStateEvent>
{
  @FXML
  private TextField folderPathTextField;

  private final FolderAnalysis folderAnalysis;
  private final FolderAnalysisService folderAnalysisService;

  public FolderAnalysisController(
    final FolderAnalysis folderAnalysis,
    final FolderAnalysisService folderAnalysisService)
  {
    this.folderAnalysis = folderAnalysis;
    this.folderAnalysisService = folderAnalysisService;
  }

  @Override
  protected void initialize()
  {
    // Usa todo o espaço horizontal disponível para folderPathTextField:
    HBox.setHgrow(folderPathTextField, Priority.ALWAYS);

    folderAnalysis.bindFolderPathToProperty(folderPathTextField.textProperty());
    folderPathTextField.setText(System.getProperty("user.home"));
  }

  @FXML
  public void analyzeFolder(final ActionEvent actionEvent)
  {
    folderAnalysisService.setOnSucceeded(this);
    folderAnalysisService.restart();
  }

  @Override
  public void handle(final WorkerStateEvent event)
  {
    final Worker worker = event.getSource();
    if (worker.getState() == Worker.State.SUCCEEDED)
    {
//      final FolderAnalysis folderAnalysis = (FolderAnalysis) worker.getValue();
    }
  }

}
