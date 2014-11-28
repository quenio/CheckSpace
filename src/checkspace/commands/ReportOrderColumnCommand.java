package checkspace.commands;

import checkspace.report.ReportColumn;
import checkspace.report.UsageReport;

import static java.lang.Integer.parseInt;

public class ReportOrderColumnCommand extends Command
{
    private static final String COMMAND_PREFIX = "c";
    private static final String REGEX_NUMBER = "\\d+";

    private final UsageReport usageReport;

    public ReportOrderColumnCommand(UsageReport usageReport)
    {
        this.usageReport = usageReport;
    }

    @Override
    public String getHelpLine()
    {
        return COMMAND_PREFIX + "N: Ordena o relatório da pasta, onde N corresponde à coluna: 1 = Nome; 2 = Espaço; 3 = Último acesso";
    }

    @Override
    public boolean accepts(String line)
    {
        final String column = extractColumn(line);
        return line.length() == 2
                && line.startsWith(COMMAND_PREFIX)
                && column.matches(REGEX_NUMBER)
                && ReportColumn.withinColumnRange(parseInt(column));
    }

    @Override
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
