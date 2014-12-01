package checkspace.analysis;

import checkspace.gui.Controller;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static javafx.beans.binding.Bindings.not;

public class FolderAnalysisController extends Controller implements EventHandler<WorkerStateEvent>
{
  @FXML private TextField folderPathTextField;
  @FXML private Button startButton;
  @FXML private Button cancelButton;

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

    startButton.managedProperty().bind(startButton.visibleProperty());
    cancelButton.visibleProperty().bind(not(startButton.visibleProperty()));
    cancelButton.managedProperty().bind(not(startButton.visibleProperty()));

    folderAnalysisService.setOnSucceeded(this);
    folderAnalysisService.setOnCancelled(this);
    folderAnalysisService.setOnFailed(this);
  }

  @FXML
  public void startFolderAnalysis(final ActionEvent actionEvent)
  {
    folderAnalysisService.restart();
    startButton.setVisible(false);
  }

  @FXML
  public void cancelFolderAnalysis(final ActionEvent actionEvent)
  {
    folderAnalysisService.cancel();
  }

  @Override
  public void handle(final WorkerStateEvent event)
  {
    final Worker worker = event.getSource();
    switch (worker.getState())
    {
      case SUCCEEDED:
      case CANCELLED:
      case FAILED:
        startButton.setVisible(true);
        break;
      default:
        // no-op
    }
  }

}
