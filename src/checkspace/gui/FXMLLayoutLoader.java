package checkspace.gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FXMLLayoutLoader implements LayoutLoader
{
  private static final String LAYOUT_FILE_EXT = "fxml";
  private static final String STYLESHEET_FILE_EXT = "css";

  private final ResourceBundle resourceBundle;
  private final Map<Class, Controller> controllerRegistry;

  public FXMLLayoutLoader(
    final ResourceBundle resourceBundle,
    final Map<Class, Controller> controllerRegistry)
  {
    this.resourceBundle = resourceBundle;
    this.controllerRegistry = controllerRegistry;
  }

  @Override
  public Parent loadRoot(final String resourceName)
  {
    try
    {
      //noinspection ConstantConditions
      final FXMLLoader fxmlLoader = new FXMLLoader(
        getResourceOfType(LAYOUT_FILE_EXT, resourceName),
        resourceBundle,
        new JavaFXBuilderFactory(),
        controllerClass -> findController(controllerClass));
      return fxmlLoader.load();
    }
    catch (final IOException exception)
    {
      exception.printStackTrace();
      return new Group();
    }
  }

  @Nullable
  private Controller findController(final Class<?> controllerClass)
  {
    final Controller controller = controllerRegistry.get(controllerClass);
    if (controller != null)
    {
      controller.onLayoutLoad(this);
    }
    else
    {
      System.out.println("WARNING: Expected controller not found: " + controllerClass.getCanonicalName());
    }
    return controller;
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
