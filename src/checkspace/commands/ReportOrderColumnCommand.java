package checkspace.commands;

import checkspace.reports.ReportColumn;
import checkspace.reports.UsageReport;

import static java.lang.Integer.parseInt;

public class ReportOrderColumnCommand
{
  private static final String COMMAND_PREFIX = "c";
  private static final String REGEX_NUMBER = "\\d+";

  private final UsageReport usageReport;

  public ReportOrderColumnCommand(UsageReport usageReport)
  {
    this.usageReport = usageReport;
  }

  public boolean execute(String line)
  {
    // Seleciona a coluna a ser ordenada:
    final int columnNumber = parseInt(extractColumn(line));
    usageReport.getReportOrder().setReportColumn(ReportColumn.fromNumber(columnNumber));

    // Imprime a tabela com a nova ordenação:
    usageReport.print();

    // Permite a execução de outros comandos após esse.
    return true;
  }

  private String extractColumn(String line)
  {
    return line.substring(1);
  }
}
