package checkspace.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Window
{
  private static final String FXML_FILE_EXT = "fxml";
  private static final String CSS_FILE_EXT = "css";

  private final Scene scene;

  public Window(
    ResourceBundle resourceBundle,
    String resourceName,
    double width,
    double height)
  {
    final Parent root = loadRoot(resourceBundle, resourceName);

    scene = new Scene(root, width, height);
    scene.getStylesheets().add(getResourceOfType(CSS_FILE_EXT, resourceName).toExternalForm());
  }

  private Parent loadRoot(ResourceBundle resourceBundle, String resourceName)
  {
    try
    {
      return FXMLLoader.load(getResourceOfType(FXML_FILE_EXT, resourceName), resourceBundle);
    } catch (IOException exception)
    {
      exception.printStackTrace();
      return new Group();
    }
  }

  private URL getResourceOfType(String resourceType, String resourceName)
  {
    return getClass().getResource(resourceName + "." + resourceType);
  }

  public void showAt(Stage stage)
  {
    stage.setScene(scene);
    stage.show();
  }

}
