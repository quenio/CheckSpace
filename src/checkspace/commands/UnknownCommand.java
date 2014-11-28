package checkspace.commands;

import checkspace.gui.IO;

public class UnknownCommand extends Command
{
  private final IO io;

  public UnknownCommand(IO io)
  {
    this.io = io;
  }

  /**
   * Nunca deve ser chamado nesta classe. *
   */
  @Override
  public String getHelpLine()
  {
    throw new UnsupportedOperationException("Nunca deve ser chamado nesta classe.");
  }

  /**
   * Nunca deve ser chamado nesta classe. *
   */
  @Override
  public boolean accepts(String line)
  {
    throw new UnsupportedOperationException("Nunca deve ser chamado nesta classe.");
  }

  @Override
  public boolean execute(String line)
  {
    io.showMessage("\nO comando digitado não foi reconhecido. Por favor, tente novamente.\n");

    // Continua execução para que o usuário possa tentar novamente.
    return true;
  }
}
