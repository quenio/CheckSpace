package checkspace.processing;

import checkspace.console.Console;

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
        return new RootFolder(new RootFolderItem[]
            {
                new RootFolderItem("Folder2", 600000, new Date()),
                new RootFolderItem("Folder3", 400000, new Date()),
                new RootFolderItem("Folder1", 200000, new Date()),
            });
    }
}
