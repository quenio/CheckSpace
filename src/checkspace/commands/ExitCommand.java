package checkspace.commands;

import checkspace.gui.IO;

/**
 * Termina a execução do aplicativo. *
 */
public class ExitCommand extends Command
{
  private static final String COMMAND = "s";

  private final IO io;

  public ExitCommand(IO io)
  {
    this.io = io;
  }

  @Override
  public String getHelpLine()
  {
    return COMMAND + ": Termina a execução do aplicativo.";
  }

  @Override
  public boolean accepts(String line)
  {
    return line.equals(COMMAND);
  }

  @Override
  public boolean execute(String line)
  {
    io.showMessage("Terminando CheckSpace...");

    // Termina a aplicação.
    return false;
  }
}
