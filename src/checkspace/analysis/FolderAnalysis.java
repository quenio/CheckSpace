package checkspace.analysis;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

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

  public void analyzeFolder(final FolderAnalyzer folderAnalyzer)
  {
    final File folder = new File(folderPath.get());
    final File[] files = folder.listFiles();

    items.clear();

    if (files == null)
    {
      folderAnalyzer.onEmptyAnalysis(folder);
    }
    else
    {
      for (final File file : files)
      {
        final FolderAnalysisItem item = folderAnalyzer.analyzeFile(file);
        if (item == null)
        {
          break;
        }
        else
        {
          items.add(item);
        }
      }
      if (files.length == items.size())
      {
        folderAnalyzer.onSuccessfulAnalysis();
      }
    }
  }

}
