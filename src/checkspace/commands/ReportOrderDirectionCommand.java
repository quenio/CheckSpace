package checkspace.commands;

import checkspace.reports.ReportDirection;
import checkspace.reports.UsageReport;

public class ReportOrderDirectionCommand
{
  private static final String HELP_LINE = "%s: Para ver os items da pasta em ordem descrente; %s: em ordem crescente.";
  private static final String CMD_DESCENDING = "od";
  private static final String CMD_ASCENDING = "oc";

  private final UsageReport usageReport;

  public ReportOrderDirectionCommand(UsageReport usageReport)
  {
    this.usageReport = usageReport;
  }

  public boolean execute(String line)
  {
    // Seleciona a ordem:
    usageReport.getReportOrder().setReportDirection(directionFromLine(line));

    // Imprime a tabela com a nova ordenação:
    usageReport.print();

    // Permite a execução de outros comandos após esse.
    return true;
  }

  private ReportDirection directionFromLine(String line)
  {
    if (line.equals(CMD_DESCENDING))
    {
      return ReportDirection.DESCENDING;
    }
    else
    {
      return ReportDirection.ASCENDING;
    }
  }

}
