package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

import java.text.DecimalFormat;

public class SpaceColumnValueFactory extends FolderAnalysisItemValueFactory<Long, String>
{
  private static final float KILOBYTE = 1024f;
  private static final float MEGABYTE = KILOBYTE * KILOBYTE;
  private static final float GIGABYTE = KILOBYTE * MEGABYTE;

  private static final String GIGABYTE_SUFFIX = "GB";
  private static final String MEGABYTE_SUFFIX = "MB";
  private static final String KILOBYTE_SUFFIX = "KB";

  private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###.##");

  @Override
  public Long getColumnValue(final FolderAnalysisItem item)
  {
    return item.getSpace();
  }

  @Override
  public String formatColumnValue(final Long space)
  {
    return formattedSpace(space);
  }

  private String formattedSpace(final long space)
  {
    final float number;
    final String suffix;

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

}
