package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LastAccessColumnValueFactory extends FolderAnalysisItemValueFactory<Date, String>
{
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

  @Override
  public Date getColumnValue(final FolderAnalysisItem item)
  {
    return item.getLastAccess();
  }

  @Override
  public String formatColumnValue(final Date date)
  {
    return DATE_FORMAT.format(date);
  }
}
