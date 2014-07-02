package checkspace.processing;

import java.util.Iterator;

/** Representa a pasta-raíz na qual foi feita a captura de informações. **/
public class RootFolder implements Iterable<RootFolderItem>
{
    private RootFolderItem[] items;

    public RootFolder(RootFolderItem[] items)
    {
        this.items = items;
    }

    @Override
    public Iterator<RootFolderItem> iterator()
    {
        return new Iterator<RootFolderItem>()
        {
            private int i = 0;

            @Override
            public boolean hasNext()
            {
                return items.length > i && items[i] != null;
            }

            @Override
            public RootFolderItem next()
            {
                return items[i++];
            }

        };
    }

}
