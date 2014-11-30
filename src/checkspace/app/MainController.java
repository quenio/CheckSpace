package checkspace.app;

import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
import checkspace.gui.LayoutLoader;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainController extends Controller
{
  public static final String FOLDER_ANALYSIS_LAYOUT = "FolderAnalysis";
  public static final String USAGE_REPORT_LAYOUT = "UsageReport";

  @FXML
  private HBox topPane;

  @FXML
  private HBox centerPane;

  private final FolderAnalysisService folderAnalysisService;

  public MainController(final FolderAnalysisService folderAnalysisService)
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

  @Override
  public void onLayoutLoad(final LayoutLoader layoutLoader)
  {
    loadLayout(layoutLoader, FOLDER_ANALYSIS_LAYOUT);
    loadLayout(layoutLoader, USAGE_REPORT_LAYOUT);
  }

  @Override
  public void initialize()
  {
    addLayoutToPane(topPane, FOLDER_ANALYSIS_LAYOUT);
    addLayoutToPane(centerPane, USAGE_REPORT_LAYOUT);
  }

}
