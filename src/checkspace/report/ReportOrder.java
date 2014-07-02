package checkspace.report;

public class ReportOrder
{
    private ReportColumn reportColumn;
    private ReportDirection reportDirection;

    public ReportOrder(ReportColumn reportColumn, ReportDirection reportDirection)
    {
        this.reportColumn = reportColumn;
        this.reportDirection = reportDirection;
    }

    public ReportColumn getReportColumn()
    {
        return reportColumn;
    }

    public ReportDirection getReportDirection()
    {
        return reportDirection;
    }
}
