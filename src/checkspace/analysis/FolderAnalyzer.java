package checkspace.analysis;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public interface FolderAnalyzer
{
  @Nullable
  FolderAnalysisItem analyzeFile(File file);

  void onEmptyAnalysis(File folder);

  void onSuccessfulAnalysis();
}
