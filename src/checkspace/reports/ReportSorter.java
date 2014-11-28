package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

import java.util.Arrays;

import static java.util.Arrays.copyOf;

public class ReportSorter
{
  private final ReportOrder reportOrder;

  public ReportSorter(ReportOrder reportOrder)
  {
    this.reportOrder = reportOrder;
  }

  public FolderAnalysisItem[] sort(FolderAnalysisItem[] items)
  {
    final FolderAnalysisItem[] copy = copyOf(items, items.length);

    Arrays.sort(copy, reportOrder.comparator());

    return copy;
  }
}
