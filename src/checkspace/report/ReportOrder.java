package checkspace.report;

public class ReportOrder
{
    private ReportColumn reportColumn = ReportColumn.NAME;
    private ReportDirection reportDirection = ReportDirection.ASCENDING;

    public ReportColumn getReportColumn()
    {
        return reportColumn;
    }

    public void setReportColumn(ReportColumn reportColumn)
    {
        this.reportColumn = reportColumn;
    }

    public ReportDirection getReportDirection()
    {
        return reportDirection;
    }

    public void setReportDirection(ReportDirection reportDirection)
    {
        this.reportDirection = reportDirection;
    }

}
