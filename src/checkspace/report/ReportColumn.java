package checkspace.report;

public enum ReportColumn
{
    NAME, SPACE, LAST_ACCESS;

    public static ReportColumn fromNumber(int columnNumber)
    {
        precondition: assert columnNumber >= 1 && columnNumber <= values().length;

        return values()[columnNumber - 1];
    }
}
