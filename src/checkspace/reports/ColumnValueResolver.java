package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

@FunctionalInterface
public interface ColumnValueResolver<T>
{
  public T getColumnValue(FolderAnalysisItem item);
}
