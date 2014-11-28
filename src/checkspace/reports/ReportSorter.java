package checkspace.reports;

import checkspace.processing.RootFolderItem;

import java.util.Arrays;

import static java.util.Arrays.copyOf;

public class ReportSorter
{
    private final ReportOrder reportOrder;

    public ReportSorter(ReportOrder reportOrder)
    {
        this.reportOrder = reportOrder;
    }

    public RootFolderItem[] sort(RootFolderItem[] items)
    {
        final RootFolderItem[] copy = copyOf(items, items.length);

        Arrays.sort(copy, reportOrder.comparator());

        return copy;
    }
}
