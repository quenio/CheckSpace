package checkspace.analysis;

import java.util.Arrays;

public class FolderAnalysis
{
  private FolderAnalysisItem[] items;

  public FolderAnalysis(FolderAnalysisItem[] items)
  {
    this.items = items;
  }

  public FolderAnalysisItem[] getItems()
  {
    return Arrays.copyOf(items, items.length);
  }
}
