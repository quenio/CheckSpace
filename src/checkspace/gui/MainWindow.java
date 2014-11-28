package checkspace.gui;

import static java.lang.String.format;

/**
 * Mostra o relatório de uso e mensagens ao usuário, e também recebe os comandos do usuário. *
 */
public class MainWindow
{
  private static final String EMPTY_LINE = "";

  /**
   * Imprime uma linha no terminal do computador *
   */
  public void showMessage(String message)
  {
    // TODO
  }

  public void showMessage(String message, Object... args)
  {
    showMessage(format(message, args));
  }

  public String askForInput(String message)
  {
    // TODO
    return null;
  }

  public String askForInput(String message, Object... args)
  {
    return askForInput(format(message, args));
  }

  public void show()
  {
    // TODO
  }
}
