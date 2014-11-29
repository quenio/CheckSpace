package checkspace.gui;

import javafx.scene.Parent;

public interface LayoutLoader
{
  Parent loadRoot(String resourceName, Controller controller);

  String loadStylesheet(String resourceName);
}
