package checkspace.analysis;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

public class FolderAnalysis
{
  private final StringProperty folderPath = new SimpleStringProperty();
  private final ObservableList<FolderAnalysisItem> items = FXCollections.observableArrayList();

  public void bindFolderPathToProperty(final StringProperty property)
  {
    folderPath.bind(property);
  }

  public void bindListToItems(final List<FolderAnalysisItem> list)
  {
    Bindings.bindContent(list, items);
  }

  public void analyzeFolder(final Supplier<Boolean> isCancelled, final FolderAnalysisWatcher folderAnalysisWatcher)
  {
    final File folder = new File(folderPath.get());
    final File[] files = folder.listFiles();

    items.clear();

    if (files == null)
    {
      folderAnalysisWatcher.onEmptyAnalysis(folder);
    }
    else
    {
      for (final File file : files)
      {
        folderAnalysisWatcher.onItemAnalysis(file);

        if (isCancelled.get())
        {
          break;
        }
        else
        {
          final FolderAnalysisItem item = new FolderAnalysisItem(file.getName());
          items.add(0, item);
          item.analyzeFile(file, isCancelled);
        }
      }
      if (files.length == items.size())
      {
        folderAnalysisWatcher.onSuccessfulAnalysis();
      }
    }
  }

}
