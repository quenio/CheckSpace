package checkspace.processing;

import java.util.Arrays;
import java.util.Iterator;

/** Representa a pasta-raíz na qual foi feita a captura de informações. **/
public class RootFolder
{
    private RootFolderItem[] items;

    public RootFolder(RootFolderItem[] items)
    {
        this.items = items;
    }

    public RootFolderItem[] getItems()
    {
        // Retorna uma cópia para que o encapsulamento não seja quebrado.
        return Arrays.copyOf(items, items.length);
    }

}
