package checkspace.analysis;

import checkspace.reports.UsageReport;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderAnalysisService extends Service<FolderAnalysis>
{
  private final StringProperty folderPathProperty;

  public FolderAnalysisService(StringProperty folderPathProperty, UsageReport usageReport)
  {
    this.folderPathProperty = folderPathProperty;

    setOnSucceeded(new FolderAnalysisEventHandler(usageReport));
  }

  @Override
  protected Task<FolderAnalysis> createTask()
  {
    return new FolderAnalysisTask(folderPathProperty.get());
  }
}
