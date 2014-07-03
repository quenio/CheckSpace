package checkspace.report;

import checkspace.console.Console;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderItem;

/** Imprime no Console as informações capturadas em RootFolder na ordem especificada por ReportOrder. **/
public class UsageReport
{
    /** Contém as informações a serem impressas. Definido por RootFolderProcessingCommand. **/
    private RootFolder rootFolder;

    // Classes utilizadas na ordenação:
    private final ReportOrder reportOrder = new ReportOrder();
    private final ReportSorter reportSorter = new ReportSorter(reportOrder);

    // O relatório é impresso no Console:
    private final Console console;

    public UsageReport(Console console)
    {
        this.console = console;
    }

    public ReportOrder getReportOrder()
    {
        return reportOrder;
    }

    public void setRootFolder(RootFolder rootFolder)
    {
        this.rootFolder = rootFolder;
    }

    public void print()
    {
        precondition: assert rootFolder != null : "RootFolder precisa ser definido antes de chamar print()";

        printHeader();
        printLines();
        printFooter();
    }

    private void printHeader()
    {
        console.printLine("\nNome          Espaço         Último Accesso");
        console.printLine("-------------------------------------------");
    }

    private void printLines()
    {
        final RootFolderItem[] sortedItems = reportSorter.sort(rootFolder.getItems());

        for (RootFolderItem item : sortedItems)
        {
            printLine(item);
        }
    }

    private void printLine(RootFolderItem item)
    {
        console.printLine(item.getName() + "   " + item.getSpace() + "   " + item.getLastAccess());
    }

    private void printFooter()
    {
        console.printLine("-------------------------------------------\n");
    }

}
