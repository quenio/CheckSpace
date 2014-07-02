package checkspace.report;

import checkspace.console.Console;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderItem;

/** Imprime as informações capturadas em RootFolder na ordem especificada por ReportOrder. **/
public class UsageReport
{
    private final Console console;

    private ReportOrder reportOrder;

    public UsageReport(Console console)
    {
        this.console = console;
    }

    public void setReportOrder(ReportOrder reportOrder)
    {
        this.reportOrder = reportOrder;
    }

    public void print(RootFolder rootFolder)
    {
        printHeader();
        printLines(rootFolder);
    }

    private void printHeader()
    {
        console.printLine("Nome          Espaço         Último Accesso");
        console.printLine("-------------------------------------------");
    }

    private void printLines(RootFolder rootFolder)
    {
        final ReportSorter reportSorter = new ReportSorter(reportOrder);
        for (RootFolderItem item : reportSorter.sort(rootFolder.getItems()))
        {
            printLine(item);
        }
    }

    private void printLine(RootFolderItem item)
    {
        console.printLine(item.getName() + "   " + item.getSpace() + "   " + item.getLastAccess());
    }

}
