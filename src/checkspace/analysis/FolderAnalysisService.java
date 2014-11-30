package checkspace.analysis;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderAnalysisService extends Service<FolderAnalysis>
{
  private final FolderAnalysisTask.Factory folderAnalysisTaskFactory;

  public FolderAnalysisService(final FolderAnalysisTask.Factory folderAnalysisTaskFactory)
  {
    this.folderAnalysisTaskFactory = folderAnalysisTaskFactory;
  }

  @Override
  protected Task<FolderAnalysis> createTask()
  {
    return folderAnalysisTaskFactory.create();
  }
}
