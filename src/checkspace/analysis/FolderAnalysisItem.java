package checkspace.analysis;

import javafx.beans.property.*;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

import static java.lang.Long.max;

/** Representa uma sub-pasta ou um arquivo dentro da pasta-raíz. */
public class FolderAnalysisItem
{
  private static final File LIBRARY_DIR = new File(System.getProperty("user.home"), "Library");

  /** O nome do arquivo ou sub-pasta. */
  private final StringProperty name = new SimpleStringProperty();

  /** O espaço utilizado por este item em bytes. */
  private final LongProperty space = new SimpleLongProperty();

  /** O último acesso efetuado a este item. */
  private final LongProperty lastAccess = new SimpleLongProperty();

  public FolderAnalysisItem(final String name)
  {
    this.name.setValue(name);
  }

  public static void bindToName(final FolderAnalysisItem folderAnalysisItem, final Property<String> property)
  {
    property.bind(folderAnalysisItem.name);
  }

  public static void bindToSpace(final FolderAnalysisItem folderAnalysisItem, final Property<Number> property)
  {
    property.bind(folderAnalysisItem.space);
  }

  public static void bindToLastAccess(final FolderAnalysisItem folderAnalysisItem, final Property<Number> property)
  {
    property.bind(folderAnalysisItem.lastAccess);
  }

  public void analyzeFile(final File file, final Supplier<Boolean> isCancelled)
  {
    space.set(0);
    sumSpaceOf(file, isCancelled);

    lastAccess.set(0);
    determineLatestAccessOf(file, isCancelled);
  }

  private void sumSpaceOf(final File file, final Supplier<Boolean> isCancelled)
  {
    if (file.isDirectory())
    {
      if (isSymlink(file) || isLibraryDir(file)) return;

      for (final File f : childrenOf(file))
      {
        if (isCancelled.get()) break;

        sumSpaceOf(f, isCancelled);
      }
    }
    else
    {
      space.set(space.get() + file.length());
    }
  }

  private void determineLatestAccessOf(final File file, final Supplier<Boolean> isCancelled)
  {
    if (isLibraryDir(file)) return;

    final long max = max(lastAccess.get(), file.lastModified());
    lastAccess.set(max);

    for (final File f : childrenOf(file))
    {
      if (isCancelled.get()) break;

      determineLatestAccessOf(f, isCancelled);
    }
  }

  private File[] childrenOf(final File folder)
  {
    final File[] files = folder.listFiles();
    return files == null ? new File[0] : files;
  }

  private static boolean isSymlink(final File file)
  {
    try
    {
      return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }
    catch (final IOException exception)
    {
      exception.printStackTrace();
      return false;
    }
  }

  private static boolean isLibraryDir(final File file)
  {
    return file.equals(LIBRARY_DIR);
  }

}

