package checkspace.reports;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Format
{
  private static final float KILOBYTE = 1024f;
  private static final float MEGABYTE = KILOBYTE * KILOBYTE;
  private static final float GIGABYTE = KILOBYTE * MEGABYTE;

  private static final String GIGABYTE_SUFFIX = "GB";
  private static final String MEGABYTE_SUFFIX = "MB";
  private static final String KILOBYTE_SUFFIX = "KB";

  private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###.##");
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

  public static String date(final Number input)
  {
    return DATE_FORMAT.format(input.longValue());
  }

  public static String space(final Number input)
  {
    final float space = input.floatValue();
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
