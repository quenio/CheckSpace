package checkspace.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateColumnValueFactory extends FolderAnalysisItemValueFactory<Date, String>
{
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

  protected DateColumnValueFactory(final ColumnValueResolver<Date> columnValueResolver)
  {
    super(columnValueResolver);
  }

  @Override
  public String formatColumnValue(final Date date)
  {
    return DATE_FORMAT.format(date);
  }
}
