package checkspace.analysis;

import javafx.concurrent.Task;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Date;
import java.util.ResourceBundle;

/** Analisa todos os arquivos e sub-pastas no caminho especificado. */
public class FolderAnalysisTask extends Task<FolderAnalysis> implements FolderAnalyzer
{
  @FunctionalInterface
  public interface Factory
  {
    FolderAnalysisTask create();
  }

  private final ResourceBundle resourceBundle;
  private final FolderAnalysis folderAnalysis;

  public FolderAnalysisTask(final ResourceBundle resourceBundle, final FolderAnalysis folderAnalysis)
  {
    this.resourceBundle = resourceBundle;
    this.folderAnalysis = folderAnalysis;
  }

  @Override
  protected FolderAnalysis call() throws Exception
  {
    updateMessageWithKey("message.analysingFolder");
    folderAnalysis.analyzeFolder(this);
    return folderAnalysis;
  }

  @Nullable
  @Override
  public FolderAnalysisItem analyzeFile(final File file)
  {
    if (isCancelled())
    {
      updateMessageWithKey("message.analysisCancelled");
      return null;
    }
    else
    {
      updateMessageWithKey("message.analysingItem", file.getName());
      return mapFileToItem(file);
    }
  }

  @Override
  public void onEmptyAnalysis(final File folder)
  {
    updateMessageWithKey("message.emptyFolder", folder.getAbsoluteFile());
  }

  @Override
  public void onSuccessfulAnalysis()
  {
    updateMessageWithKey("message.analysisComplete");
  }

  private FolderAnalysisItem mapFileToItem(final File file)
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
