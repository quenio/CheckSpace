package checkspace.report;

import checkspace.console.Console;
import checkspace.processing.RootFolder;
import checkspace.processing.RootFolderItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

/** Imprime no Console as informações capturadas em RootFolder na ordem especificada por ReportOrder. **/
public class UsageReport
{
    private static final String HEADER_FORMAT = "%n%-50s %-20s %-17s";
    private static final String ITEM_FORMAT = "%-50s %-20s %-17s";
    private static final String BAR_FORMAT = "%089d";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###.##");

    private static final float KILOBYTE = 1024f;
    private static final float MEGABYTE = KILOBYTE * KILOBYTE;
    private static final float GIGABYTE = KILOBYTE * MEGABYTE;

    private static final String GIGABYTE_SUFFIX = "GB";
    private static final String MEGABYTE_SUFFIX = "MB";
    private static final String KILOBYTE_SUFFIX = "KB";

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
        console.printLine(HEADER_FORMAT, "Nome", "Espaço", "Último Acesso");
        printBar();
    }

    private void printLines()
    {
        final RootFolderItem[] sortedItems = reportSorter.sort(rootFolder.getItems());

        for (RootFolderItem item : sortedItems)
        {
            printItem(item);
        }
    }

    private void printItem(RootFolderItem item)
    {
        if (isClearText(item.getName()))
        {
            console.printLine(
                    ITEM_FORMAT,
                    item.getName(),
                    formattedSpace(item.getSpace()),
                    formattedDate(item.getLastAccess()));
        }
    }

    /** Retorna true se o texto não possui caracteres de controle. **/
    private boolean isClearText(String text)
    {
        return !text.contains("\r") && !text.contains("\t");
    }

    private String formattedSpace(long space)
    {
        float number;
        String suffix;

        if (space > GIGABYTE)
        {
            number = space / GIGABYTE;
            suffix = GIGABYTE_SUFFIX;
        }
        else if (space > MEGABYTE)
        {
            number = space / MEGABYTE;
            suffix = MEGABYTE_SUFFIX;
        }
        else
        {
            number = space / KILOBYTE;
            suffix = KILOBYTE_SUFFIX;
        }

        return NUMBER_FORMAT.format(number) + " " + suffix;
    }

    private String formattedDate(Date date)
    {
        return DATE_FORMAT.format(date);
    }

    private void printFooter()
    {
        printBar();
        console.printLine("");
    }

    private void printBar()
    {
        console.printLine(format(BAR_FORMAT, 0).replace("0","-"));
    }

}
