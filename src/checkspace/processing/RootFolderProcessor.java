package checkspace.processing;

import checkspace.console.Console;

import java.io.File;
import java.util.Date;

public class RootFolderProcessor
{
    private final Console console;

    public RootFolderProcessor(Console console)
    {
        this.console = console;
    }

    /** Processa todos os arquivos e sub-pastas no caminho especificado e retorna RootFolder com a informações. **/
    public RootFolder process(String path)
    {
        final File root = new File(path);

        assert root.isDirectory() : "Path must refer to a folder: " + path;

        final RootFolderItem[] items = map(root.listFiles());

        return new RootFolder(items);
    }

    private RootFolderItem[] map(File[] files)
    {
        final RootFolderItem[] items = new RootFolderItem[files.length];

        int i = 0;
        for (File file : files)
        {
            console.printLine("Processando: " + file.getName());

            items[i++] = map(file);
        }

        return items;
    }

    private RootFolderItem map(File file)
    {
        return new RootFolderItem(file.getName(), spaceOf(file), lastAccessOf(file));
    }

    private long spaceOf(File file)
    {
        if (file.isDirectory())
        {
            long space = 0;

            for (File f : file.listFiles())
            {
                space += spaceOf(f);
            }

            return space;
        }
        else
        {
            return file.length();
        }
    }

    private Date lastAccessOf(File file)
    {
        return new Date(lastAccessOf(file, 0));
    }

    private long lastAccessOf(File file, long lastAccess)
    {
        if (lastAccess < file.lastModified())
        {
            lastAccess = file.lastModified();
        }

        if (file.isDirectory())
        {
            for (File f : file.listFiles())
            {
                lastAccess = lastAccessOf(f, lastAccess);
            }
        }

        return lastAccess;
    }
}
