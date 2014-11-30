package checkspace.reports;

import checkspace.analysis.FolderAnalysis;
import checkspace.analysis.FolderAnalysisItem;
import checkspace.gui.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UsageReportController extends Controller
{
  private final FolderAnalysis folderAnalysis;

  @FXML
  public TableView<FolderAnalysisItem> usageReport;

  @FXML
  private TableColumn<FolderAnalysisItem, String> nameColumn;

  @FXML
  private TableColumn<FolderAnalysisItem, String> spaceColumn;

  @FXML
  private TableColumn<FolderAnalysisItem, String> lastAccessColumn;

  public UsageReportController(final FolderAnalysis folderAnalysis)
  {
    this.folderAnalysis = folderAnalysis;
  }

  @Override
  protected void initialize()
  {
    folderAnalysis.bindListToItems(usageReport.getItems());
    ColumnBinding.of(nameColumn, FolderAnalysisItem::getName);
    ColumnBinding.of(spaceColumn, FolderAnalysisItem::getSpace, Format::space);
    ColumnBinding.of(lastAccessColumn, FolderAnalysisItem::getLastAccess, Format::date);
  }

}
