package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public abstract class FolderAnalysisItemValueFactory<T, S>
  implements Callback<TableColumn.CellDataFeatures<FolderAnalysisItem, S>, ObservableValue<S>>
{
  @Override
  public ObservableValue<S> call(final TableColumn.CellDataFeatures<FolderAnalysisItem, S> row)
  {
    final T columnValue = getColumnValue(row.getValue());
    return new ReadOnlyObjectWrapper<>(formatColumnValue(columnValue));
  }

  public abstract T getColumnValue(FolderAnalysisItem item);

  public abstract S formatColumnValue(T columnValue);
}
