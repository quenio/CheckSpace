package checkspace.analysis;

import java.util.Arrays;

public class FolderAnalysis
{
  private final FolderAnalysisItem[] items;

  public FolderAnalysis(final FolderAnalysisItem[] items)
  {
    this.items = items;
  }

  public FolderAnalysisItem[] getItems()
  {
    return Arrays.copyOf(items, items.length);
  }
}
