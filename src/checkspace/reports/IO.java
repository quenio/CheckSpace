package checkspace.reports;

import static java.lang.String.format;

public class IO
{

  public void showMessage(String message)
  {
    System.out.println(message);
  }

  public void showMessage(String message, Object... args)
  {
    showMessage(format(message, args));
  }

  public String askForInput(String message)
  {
    // TODO
    return "";
  }

  public String askForInput(String message, Object... args)
  {
    return askForInput(format(message, args));
  }
}
