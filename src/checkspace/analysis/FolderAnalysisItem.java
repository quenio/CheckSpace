package checkspace.analysis;

import java.util.Date;

/** Representa uma sub-pasta ou um arquivo dentro da pasta-raíz. */
public class FolderAnalysisItem
{
  private final String name;
  private final long space;
  private final Date lastAccess;

  public FolderAnalysisItem(final String name, final long space, final Date lastAccess)
  {
    this.name = name;
    this.space = space;
    this.lastAccess = lastAccess;
  }

  /** O nome do arquivo ou sub-pasta. */
  public String getName()
  {
    return name;
  }

  /** O espaço utilizado por este item em bytes. */
  public long getSpace()
  {
    return space;
  }

  /** O lastAccess do último acesso efetuado a este item */
  public Date getLastAccess()
  {
    return lastAccess;
  }

}

