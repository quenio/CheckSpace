package checkspace.commands;

import checkspace.reports.IO;

/**
 * Termina a execução do aplicativo. *
 */
public class ExitCommand
{
  private static final String COMMAND = "s";

  private final IO io;

  public ExitCommand(IO io)
  {
    this.io = io;
  }

  public boolean execute(String line)
  {
    io.showMessage("Terminando CheckSpace...");

    // Termina a aplicação.
    return false;
  }
}
