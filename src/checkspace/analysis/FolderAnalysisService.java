package checkspace.analysis;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderAnalysisService extends Service<FolderAnalysis>
{
  private final StringProperty folderPath = new SimpleStringProperty();
  private final FolderAnalysisTask.Factory folderAnalysisTaskFactory;

  public FolderAnalysisService(final FolderAnalysisTask.Factory folderAnalysisTaskFactory)
  {
    this.folderAnalysisTaskFactory = folderAnalysisTaskFactory;
  }

  public StringProperty folderPathProperty()
  {
    return folderPath;
  }

  @Override
  protected Task<FolderAnalysis> createTask()
  {
    return folderAnalysisTaskFactory.create(folderPath.get());
  }
}
