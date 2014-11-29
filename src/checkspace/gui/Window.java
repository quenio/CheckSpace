package checkspace.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window
{
  private final String resourceName;
  private final String title;
  private final double width;
  private final double height;
  private Scene scene;

  public Window(
    final String resourceName,
    final String title,
    final double width,
    final double height)
  {
    this.resourceName = resourceName;
    this.title = title;
    this.width = width;
    this.height = height;
  }

  public void loadLayout(final LayoutLoader layoutLoader)
  {
    final Parent root = layoutLoader.loadRoot(resourceName);
    scene = new Scene(root, width, height);

    final String stylesheet = layoutLoader.loadStylesheet(resourceName);
    scene.getStylesheets().add(stylesheet);
  }

  public void showOn(final Stage stage)
  {
    stage.setTitle(title);
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setScene(scene);
    stage.show();
  }

}
