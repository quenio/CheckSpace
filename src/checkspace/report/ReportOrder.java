package checkspace.report;

import checkspace.processing.RootFolderItem;

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

    public Comparator<RootFolderItem> comparator()
    {
        final Comparator<RootFolderItem> comparator = reportColumn.comparator();

        if (reportDirection == ReportDirection.DESCENDING) {
            return comparator.reversed();
        } else {
            return comparator;
        }
    }
}
