package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

public class NameColumnValueFactory extends FolderAnalysisItemValueFactory<String, String>
{
  @Override
  public ObservableValue<String> call(final TableColumn.CellDataFeatures<FolderAnalysisItem, String> row)
  {
    final String columnValue = row.getValue().getName();
    return new ReadOnlyObjectWrapper<>(columnValue);
  }

  @Override
  public String getColumnValue(final FolderAnalysisItem item)
  {
    return item.getName();
  }

  @Override
  public String formatColumnValue(final String name)
  {
    return name.trim();
  }
}
