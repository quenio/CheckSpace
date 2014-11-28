package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

import java.util.Comparator;

public class ReportOrder
{
  private ReportColumn reportColumn = ReportColumn.NAME;
  private ReportDirection reportDirection = ReportDirection.ASCENDING;

  public void setReportColumn(ReportColumn reportColumn)
  {
    this.reportColumn = reportColumn;
  }

  public void setReportDirection(ReportDirection reportDirection)
  {
    this.reportDirection = reportDirection;
  }

  public Comparator<FolderAnalysisItem> comparator()
  {
    final Comparator<FolderAnalysisItem> comparator = reportColumn.comparator();

    if (reportDirection == ReportDirection.DESCENDING)
    {
      return comparator.reversed();
    }
    else
    {
      return comparator;
    }
  }
}
