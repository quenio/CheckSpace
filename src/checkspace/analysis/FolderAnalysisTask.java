package checkspace.analysis;

import javafx.concurrent.Task;

import java.io.File;
import java.util.Date;
import java.util.ResourceBundle;

/** Analisa todos os arquivos e sub-pastas no caminho especificado. */
public class FolderAnalysisTask extends Task<FolderAnalysis>
{
  @FunctionalInterface
  public interface Factory
  {
    FolderAnalysisTask create(String path);
  }

  private final ResourceBundle resourceBundle;
  private final File rootFolder;

  public FolderAnalysisTask(final ResourceBundle resourceBundle, final String path)
  {
    this.resourceBundle = resourceBundle;
    this.rootFolder = new File(path);
  }

  @Override
  protected FolderAnalysis call() throws Exception
  {
    updateMessageWithKey("message.analysingFolder");

    return new FolderAnalysis(map(childrenOf(rootFolder)));
  }

  private FolderAnalysisItem[] map(final File[] files)
  {
    final FolderAnalysisItem[] items = new FolderAnalysisItem[files.length];
    int i = 0;
    for (final File file : files)
    {
      if (isCancelled())
      {
        updateMessageWithKey("message.analysisCancelled");
      }
      else
      {
        updateMessageWithKey("message.analysingItem", file.getName());
        items[i++] = map(file);
      }
    }

    if (items.length == 0)
    {
      updateMessageWithKey("message.emptyFolder", rootFolder.getAbsoluteFile());
    }
    else
    {
      updateMessageWithKey("message.analysisComplete");
    }

    return items;
  }

  private FolderAnalysisItem map(final File file)
  {
    return new FolderAnalysisItem(file.getName(), spaceOf(file), lastAccessOf(file));
  }

  private long spaceOf(final File file)
  {
    if (file.isDirectory())
    {
      long space = 0;

      for (final File f : childrenOf(file))
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

  private Date lastAccessOf(final File file)
  {
    return new Date(lastAccessOf(file, 0));
  }

  private long lastAccessOf(final File file, long lastAccess)
  {
    if (lastAccess < file.lastModified())
    {
      lastAccess = file.lastModified();
    }

    for (final File f : childrenOf(file))
    {
      lastAccess = lastAccessOf(f, lastAccess);
    }

    return lastAccess;
  }

  private File[] childrenOf(final File folder)
  {
    final File[] files = folder.listFiles();
    return files == null ? new File[0] : files;
  }

  private void updateMessageWithKey(final String key)
  {
    updateMessage(resourceBundle.getString(key));
  }

  private void updateMessageWithKey(final String key, final Object... args)
  {
    updateMessage(String.format(resourceBundle.getString(key), args));
  }
}
