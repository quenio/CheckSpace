package checkspace.analysis;

import java.io.File;

public interface FolderAnalysisWatcher
{
  void onItemAnalysis(File file);

  void onEmptyAnalysis(File folder);

  void onSuccessfulAnalysis();
}
