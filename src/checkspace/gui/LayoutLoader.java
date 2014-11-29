package checkspace.gui;

import javafx.scene.Parent;

public interface LayoutLoader
{
  Parent loadRoot(String resourceName);

  String loadStylesheet(String resourceName);
}
