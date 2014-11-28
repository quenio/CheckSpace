package checkspace.gui;

import javafx.fxml.FXMLLoader;
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
    double height) throws IOException
  {
    scene = new Scene(
      FXMLLoader.load(getResourceOfType(FXML_FILE_EXT, resourceName), resourceBundle),
      width,
      height);

    scene.getStylesheets().add(getResourceOfType(CSS_FILE_EXT, resourceName).toExternalForm());
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
