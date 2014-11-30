package checkspace.analysis;

import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class FolderAnalysisEventHandler implements EventHandler<WorkerStateEvent>
{
  @Override
  public void handle(final WorkerStateEvent event)
  {
    final Worker worker = event.getSource();
    if (worker.getState() == Worker.State.SUCCEEDED)
    {
//      final FolderAnalysis folderAnalysis = (FolderAnalysis) worker.getValue();
    }
  }
}
