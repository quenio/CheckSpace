package checkspace.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class Controller
{
  private Map<String, Parent> layoutRoots = new HashMap<>();

  protected void onLayoutLoad(final LayoutLoader layoutLoader)
  {
    // no-op - subclasses devem chamar loadLayout(resourceName)
  }

  protected void loadLayout(final LayoutLoader layoutLoader, final String resourceName)
  {
    layoutRoots.put(resourceName, layoutLoader.loadRoot(resourceName));
  }

  protected void addLayoutToPane(final Pane pane, final String resourceName)
  {
    if (layoutRoots.containsKey(resourceName))
    {
      pane.getChildren().add(layoutRoots.get(resourceName));
      layoutRoots.remove(resourceName);
    }
  }

  @FXML
  protected void initialize()
  {
    // no-op - subclasses devem acessar controles neste método; não antes.
  }
}
