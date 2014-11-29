package checkspace.gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLLayoutLoader implements LayoutLoader
{
  private static final String LAYOUT_FILE_EXT = "fxml";
  private static final String STYLESHEET_FILE_EXT = "css";

  private final ResourceBundle resourceBundle;

  public FXMLLayoutLoader(final ResourceBundle resourceBundle)
  {
    this.resourceBundle = resourceBundle;
  }

  @Override
  public Parent loadRoot(final String resourceName, final Controller controller)
  {
    try
    {
      final FXMLLoader fxmlLoader = new FXMLLoader(
        getResourceOfType(LAYOUT_FILE_EXT, resourceName),
        resourceBundle,
        new JavaFXBuilderFactory(),
        param -> controller);
      return fxmlLoader.load();
    }
    catch (final IOException exception)
    {
      exception.printStackTrace();
      return new Group();
    }
  }

  @Override
  public String loadStylesheet(final String resourceName)
  {
    return getResourceOfType(STYLESHEET_FILE_EXT, resourceName).toExternalForm();
  }

  private URL getResourceOfType(final String resourceType, final String resourceName)
  {
    return getClass().getResource(resourceName + "." + resourceType);
  }

}
