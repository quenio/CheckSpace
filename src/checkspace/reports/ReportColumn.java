package checkspace.reports;

import checkspace.analysis.FolderAnalysisItem;

import java.util.Comparator;

public enum ReportColumn
{
  NAME
    {
      @Override
      public Comparator<FolderAnalysisItem> comparator()
      {
        return new Comparator<FolderAnalysisItem>()
        {
          @Override
          public int compare(FolderAnalysisItem item1, FolderAnalysisItem item2)
          {
            return item1.getName().compareTo(item2.getName());
          }
        };
      }
    },

  SPACE
    {
      @Override
      public Comparator<FolderAnalysisItem> comparator()
      {
        return new Comparator<FolderAnalysisItem>()
        {
          @Override
          public int compare(FolderAnalysisItem item1, FolderAnalysisItem item2)
          {
            return new Long(item1.getSpace()).compareTo(item2.getSpace());
          }
        };
      }
    },

  LAST_ACCESS
    {
      @Override
      public Comparator<FolderAnalysisItem> comparator()
      {
        return new Comparator<FolderAnalysisItem>()
        {
          @Override
          public int compare(FolderAnalysisItem item1, FolderAnalysisItem item2)
          {
            return item1.getLastAccess().compareTo(item2.getLastAccess());
          }
        };
      }
    };

  public static ReportColumn fromNumber(int columnNumber)
  {
    precondition:
    assert withinColumnRange(columnNumber) : "columnNumber out of bounds";

    return values()[columnNumber - 1];
  }

  public static boolean withinColumnRange(int columnNumber)
  {
    return columnNumber >= 1 && columnNumber <= values().length;
  }

  public abstract Comparator<FolderAnalysisItem> comparator();
}
