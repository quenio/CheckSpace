package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnValue
{
  public static <T> void of(
    final TableColumn<FolderAnalysisItem, String> column,
    final Resolver<T> resolver)
  {
    column.setCellValueFactory(new Factory<>(resolver));
  }

  public static <T> void of(
    final TableColumn<FolderAnalysisItem, String> column,
    final Resolver<T> resolver,
    final Formatter<T> formatter)
  {
    column.setCellValueFactory(new Factory<>(resolver, formatter));
  }

  @FunctionalInterface
  public interface Resolver<T>
  {
    public T resolveColumnValue(FolderAnalysisItem item);
  }

  @FunctionalInterface
  public interface Formatter<T>
  {
    public abstract String formatColumnValue(T columnValue);
  }

  public static class Factory<T>
    implements Callback<TableColumn.CellDataFeatures<FolderAnalysisItem, String>, ObservableValue<String>>
  {
    private final Resolver<T> resolver;
    private final Formatter<T> formatter;

    public Factory(final Resolver<T> resolver)
    {
      this(resolver, (T item) -> item.toString());
    }

    public Factory(
      final Resolver<T> resolver,
      final Formatter<T> formatter)
    {
      this.resolver = resolver;
      this.formatter = formatter;
    }

    @Override
    public ObservableValue<String> call(final TableColumn.CellDataFeatures<FolderAnalysisItem, String> row)
    {
      final T columnValue = resolver.resolveColumnValue(row.getValue());
      return new ReadOnlyObjectWrapper<>(formatter.formatColumnValue(columnValue));
    }
  }

}
