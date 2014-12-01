package checkspace.analysis;

import javafx.concurrent.Task;

import java.io.File;
import java.util.ResourceBundle;

/** Analisa todos os arquivos e sub-pastas no caminho especificado. */
public class FolderAnalysisTask extends Task<FolderAnalysis> implements FolderAnalysisWatcher
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

    folderAnalysis.analyzeFolder(this::isCancelled, this);

    return folderAnalysis;
  }

  @Override
  public void onItemAnalysis(final File file)
  {
    if (isCancelled())
    {
      updateMessageWithKey("message.analysisCancelled");
    }
    else
    {
      updateMessageWithKey("message.analysingItem", file.getName());
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

  private void updateMessageWithKey(final String key)
  {
    updateMessage(resourceBundle.getString(key));
  }

  private void updateMessageWithKey(final String key, final Object... args)
  {
    updateMessage(String.format(resourceBundle.getString(key), args));
  }

}
