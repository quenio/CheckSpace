package checkspace.analysis;

import checkspace.reports.UsageReport;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class FolderAnalysisEventHandler implements EventHandler<WorkerStateEvent>
{
  private final UsageReport usageReport;

  public FolderAnalysisEventHandler(UsageReport usageReport)
  {
    this.usageReport = usageReport;
  }

  @Override
  public void handle(WorkerStateEvent event)
  {
    final FolderAnalysis folderAnalysis = (FolderAnalysis) event.getSource().getValue();
    usageReport.setFolderAnalysis(folderAnalysis);
    usageReport.print();
  }
}
