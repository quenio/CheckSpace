package checkspace.app;

import checkspace.analysis.FolderAnalysis;
import checkspace.analysis.FolderAnalysisEventHandler;
import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
import checkspace.gui.LayoutLoader;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MainController extends Controller
{
  public static final String USAGE_REPORT_LAYOUT = "UsageReport";

  private final FolderAnalysis folderAnalysis;
  private final FolderAnalysisService folderAnalysisService;
  private final FolderAnalysisEventHandler folderAnalysisEventHandler;

  @FXML
  private HBox analyzeFolderPane;

  @FXML
  private HBox mainPage;

  @FXML
  private TextField folderPathTextField;

  public MainController(
    final FolderAnalysis folderAnalysis,
    final FolderAnalysisService folderAnalysisService,
    final FolderAnalysisEventHandler folderAnalysisEventHandler)
  {
    this.folderAnalysis = folderAnalysis;
    this.folderAnalysisService = folderAnalysisService;
    this.folderAnalysisEventHandler = folderAnalysisEventHandler;
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

  @Override
  public void onLayoutLoad(final LayoutLoader layoutLoader)
  {
    loadLayout(layoutLoader, USAGE_REPORT_LAYOUT);
  }

  @Override
  public void initialize()
  {
    addLayoutToPane(mainPage, USAGE_REPORT_LAYOUT);

    // Dá ao nodo especificado todo o espaço horizontal disponível:
    HBox.setHgrow(folderPathTextField, Priority.ALWAYS);
    HBox.setHgrow(mainPage.getChildren().get(0), Priority.ALWAYS);

    folderAnalysisService.setOnSucceeded(folderAnalysisEventHandler);
    folderAnalysis.bindFolderPathToProperty(folderPathTextField.textProperty());
    folderPathTextField.setText(System.getProperty("user.home"));
  }

  @FXML
  public void analyzeFolder()
  {
    folderAnalysisService.restart();
  }

}
