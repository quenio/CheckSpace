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
            items[i++] = map(file);
        }

        return items;
    }

    private RootFolderItem map(File file)
    {
        return new RootFolderItem(file.getName(), file.length(), new Date(file.lastModified()));
    }
}
