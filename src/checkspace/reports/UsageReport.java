package checkspace.reports;

import checkspace.analysis.FolderAnalysis;
import checkspace.analysis.FolderAnalysisItem;
import checkspace.gui.IO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

/**
 * Imprime no Console as informações capturadas em RootFolder na ordem especificada por ReportOrder. *
 */
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

  /**
   * Contém as informações a serem impressas. Definido por RootFolderProcessingCommand. *
   */
  private FolderAnalysis folderAnalysis;

  // Classes utilizadas na ordenação:
  private final ReportOrder reportOrder = new ReportOrder();
  private final ReportSorter reportSorter = new ReportSorter(reportOrder);

  private final IO io;

  public UsageReport(IO io)
  {
    this.io = io;
  }

  public ReportOrder getReportOrder()
  {
    return reportOrder;
  }

  public void setFolderAnalysis(FolderAnalysis folderAnalysis)
  {
    this.folderAnalysis = folderAnalysis;
  }

  public void print()
  {
    precondition:
    assert folderAnalysis != null : "RootFolder precisa ser definido antes de chamar print()";

    printHeader();
    printLines();
    printFooter();
  }

  private void printHeader()
  {
    io.showMessage(HEADER_FORMAT, "Nome", "Espaço", "Último Acesso");
    printBar();
  }

  private void printLines()
  {
    final FolderAnalysisItem[] sortedItems = reportSorter.sort(folderAnalysis.getItems());

    for (FolderAnalysisItem item : sortedItems)
    {
      printItem(item);
    }
  }

  private void printItem(FolderAnalysisItem item)
  {
    if (isClearText(item.getName()))
    {
      io.showMessage(
        ITEM_FORMAT,
        item.getName(),
        formattedSpace(item.getSpace()),
        formattedDate(item.getLastAccess()));
    }
  }

  /**
   * Retorna true se o texto não possui caracteres de controle. *
   */
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
    io.showMessage("");
  }

  private void printBar()
  {
    io.showMessage(format(BAR_FORMAT, 0).replace("0", "-"));
  }

}
