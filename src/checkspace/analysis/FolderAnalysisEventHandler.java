package checkspace.analysis;

import checkspace.reports.UsageReport;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class FolderAnalysisEventHandler implements EventHandler<WorkerStateEvent>
{
  private final UsageReport usageReport;

  public FolderAnalysisEventHandler(final UsageReport usageReport)
  {
    this.usageReport = usageReport;
  }

  @Override
  public void handle(final WorkerStateEvent event)
  {
    final Worker worker = event.getSource();
    if (worker.getState() == Worker.State.SUCCEEDED)
    {
      final FolderAnalysis folderAnalysis = (FolderAnalysis) worker.getValue();
      usageReport.setFolderAnalysis(folderAnalysis);
      usageReport.print();
    }
  }
}
