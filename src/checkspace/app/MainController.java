package checkspace.app;

import checkspace.analysis.FolderAnalysisEventHandler;
import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
import checkspace.gui.LayoutLoader;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class MainController extends Controller
{
  public static final String USAGE_REPORT_TABLE_LAYOUT = "UsageReportTable";

  private final FolderAnalysisService folderAnalysisService;
  private final FolderAnalysisEventHandler folderAnalysisEventHandler;

  @FXML
  private FlowPane mainPage;

  @FXML
  private GridPane analyzeFolderPane;

  @FXML
  private TextField folderPathTextField;

  public MainController(
    final FolderAnalysisService folderAnalysisService,
    final FolderAnalysisEventHandler folderAnalysisEventHandler)
  {
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
    loadLayout(layoutLoader, USAGE_REPORT_TABLE_LAYOUT);
  }

  @Override
  public void initialize()
  {
    addLayoutToPane(mainPage, USAGE_REPORT_TABLE_LAYOUT);

    folderAnalysisService.setOnSucceeded(folderAnalysisEventHandler);
    folderAnalysisService.folderPathProperty().bind(folderPathTextField.textProperty());
    folderPathTextField.setText(System.getProperty("user.home"));
  }

  @FXML
  public void analyzeFolder()
  {
    folderAnalysisService.reset();
    folderAnalysisService.start();
  }

}
