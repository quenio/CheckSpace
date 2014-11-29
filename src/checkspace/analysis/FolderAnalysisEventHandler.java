package checkspace.analysis;

import checkspace.reports.UsageReport;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class FolderAnalysisEventHandler implements EventHandler<WorkerStateEvent>
{
  private final UsageReport usageReport;
  private final FolderAnalysisService folderAnalysisService;

  public FolderAnalysisEventHandler(UsageReport usageReport, FolderAnalysisService folderAnalysisService)
  {
    this.usageReport = usageReport;
    this.folderAnalysisService = folderAnalysisService;
  }

  @Override
  public void handle(WorkerStateEvent event)
  {
    final Worker worker = event.getSource();
    if (worker.getState() == Worker.State.SUCCEEDED)
    {
      final FolderAnalysis folderAnalysis = (FolderAnalysis) worker.getValue();
      usageReport.setFolderAnalysis(folderAnalysis);
      usageReport.print();
      folderAnalysisService.reset();
    }
  }
}
