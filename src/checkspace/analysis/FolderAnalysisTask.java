package checkspace.analysis;

import javafx.concurrent.Task;

import java.io.File;
import java.util.Date;

/** Analisa todos os arquivos e sub-pastas no caminho especificado. */
public class FolderAnalysisTask extends Task<FolderAnalysis>
{
  private final File rootFolder;

  public FolderAnalysisTask(String path)
  {
    this.rootFolder = new File(path);
  }

  @Override
  protected FolderAnalysis call() throws Exception
  {
    updateMessage("message.analysingFolder");

    final FolderAnalysis folderAnalysis = analyzeFiles();

    if (folderAnalysis.getItems().length == 0)
    {
      updateMessage(String.format("Pasta vazia ou não existe: %s", rootFolder.getAbsoluteFile()));
    }
    else
    {
      updateMessage("message.analysisComplete");
    }

    return folderAnalysis;
  }

  private FolderAnalysis analyzeFiles()
  {
    return new FolderAnalysis(map(listFiles()));
  }

  private File[] listFiles()
  {
    return rootFolder.isDirectory() ? rootFolder.listFiles() : new File[0];
  }

  private FolderAnalysisItem[] map(File[] files)
  {
    final FolderAnalysisItem[] items = new FolderAnalysisItem[files.length];

    int i = 0;
    for (File file : files)
    {
      if (isCancelled())
      {
        updateMessage("Análise canceldada.");
      }
      else
      {
        updateMessage("Analisando: " + file.getName());
        items[i++] = map(file);
      }
    }

    return items;
  }

  private FolderAnalysisItem map(File file)
  {
    return new FolderAnalysisItem(file.getName(), spaceOf(file), lastAccessOf(file));
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
