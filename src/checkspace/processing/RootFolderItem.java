package checkspace.processing;

import java.util.Date;

/** Representa uma sub-pasta ou um arquivo dentro da pasta-raíz. **/
public class RootFolderItem
{
    private final String name;
    private final long space;
    private final Date lastAccess;

    public RootFolderItem(String name, long space, Date lastAccess)
    {
        this.name = name;
        this.space = space;
        this.lastAccess = lastAccess;
    }

    /** O nome do arquivo ou sub-folder. **/
    public String getName()
    {
        return name;
    }

    /** O espaço utilizado por este item em bytes. **/
    public long getSpace()
    {
        return space;
    }

    /** O lastAccess do último acesso efetuado a este item **/
    public Date getLastAccess()
    {
        return lastAccess;
    }

}

