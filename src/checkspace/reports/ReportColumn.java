package checkspace.reports;

import checkspace.processing.RootFolderItem;

import java.util.Comparator;

public enum ReportColumn
{
    NAME
    {
        @Override
        public Comparator<RootFolderItem> comparator()
        {
            return new Comparator<RootFolderItem>()
            {
                @Override
                public int compare(RootFolderItem item1, RootFolderItem item2)
                {
                    return item1.getName().compareTo(item2.getName());
                }
            };
        }
    },

    SPACE
    {
        @Override
        public Comparator<RootFolderItem> comparator()
        {
            return new Comparator<RootFolderItem>()
            {
                @Override
                public int compare(RootFolderItem item1, RootFolderItem item2)
                {
                    return new Long(item1.getSpace()).compareTo(item2.getSpace());
                }
            };
        }
    },

    LAST_ACCESS
    {
        @Override
        public Comparator<RootFolderItem> comparator()
        {
            return new Comparator<RootFolderItem>()
            {
                @Override
                public int compare(RootFolderItem item1, RootFolderItem item2)
                {
                    return item1.getLastAccess().compareTo(item2.getLastAccess());
                }
            };
        }
    };

    public static ReportColumn fromNumber(int columnNumber)
    {
        precondition: assert withinColumnRange(columnNumber) : "columnNumber out of bounds";

        return values()[columnNumber - 1];
    }

    public static boolean withinColumnRange(int columnNumber)
    {
        return columnNumber >= 1 && columnNumber <= values().length;
    }

    public abstract Comparator<RootFolderItem> comparator();
}
