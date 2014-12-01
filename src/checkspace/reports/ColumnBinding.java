package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnBinding
{
  public static <T> void of(
    final TableColumn<FolderAnalysisItem, T> column,
    final Binder<T> binder)
  {
    column.setCellValueFactory(new ValueFactory<>(binder));
    column.setCellFactory(new CellFactory<>(Object::toString));
  }

  public static <T> void of(
    final TableColumn<FolderAnalysisItem, T> column,
    final Binder<T> binder,
    final Formatter<T> formatter)
  {
    column.setCellValueFactory(new ValueFactory<>(binder));
    column.setCellFactory(new CellFactory<>(formatter));
  }

  @FunctionalInterface
  public interface Binder<T>
  {
    public void bind(FolderAnalysisItem folderAnalysisItem, Property<T> property);
  }

  @FunctionalInterface
  public interface Formatter<T>
  {
    public abstract String formatColumnValue(T columnValue);
  }

  public static class ValueFactory<T>
    implements Callback<TableColumn.CellDataFeatures<FolderAnalysisItem, T>, ObservableValue<T>>
  {
    private final Binder<T> binder;

    public ValueFactory(final Binder<T> binder)
    {
      this.binder = binder;
    }

    @Override
    public ObservableValue<T> call(final TableColumn.CellDataFeatures<FolderAnalysisItem, T> row)
    {
      final Property<T> property = new SimpleObjectProperty<>();
      binder.bind(row.getValue(), property);
      return property;
    }
  }

  public static class CellFactory<T>
    implements Callback<TableColumn<FolderAnalysisItem, T>, TableCell<FolderAnalysisItem, T>>
  {
    private final Formatter<T> formatter;

    public CellFactory(final Formatter<T> formatter)
    {
      this.formatter = formatter;
    }

    @Override
    public TableCell<FolderAnalysisItem, T> call(final TableColumn<FolderAnalysisItem, T> column)
    {
      return new TableCell<FolderAnalysisItem, T>()
      {
        @Override
        protected void updateItem(final T item, final boolean empty)
        {
          super.updateItem(item, empty);
          setText(empty ? null : formatter.formatColumnValue(item));
        }
      };
    }
  }

}
