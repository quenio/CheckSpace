package checkspace.app;

import checkspace.gui.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsageReportTableController extends Controller
{
  @FXML
  private TableColumn<Object, Object> nameColumn;

  @FXML
  private TableColumn<Object, Object> spaceColumn;

  @FXML
  private TableColumn<Object, Object> lastAccessColumn;

  @Override
  protected void initialize()
  {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    spaceColumn.setCellValueFactory(new PropertyValueFactory<>("space"));
    lastAccessColumn.setCellValueFactory(new PropertyValueFactory<>("lastAccess"));
  }
}
