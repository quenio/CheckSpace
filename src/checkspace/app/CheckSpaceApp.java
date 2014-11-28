package checkspace.app;

import checkspace.gui.Window;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class CheckSpaceApp extends Application
{
  private static final String APP_BUNDLE_NAME = "checkspace.bundles.CheckSpace";
  private static final String MAIN_WINDOW_TITLE = "CheckSpace";
  private static final String MAIN_WINDOW_RESOURCE_NAME = "MainWindow";
  private static final int MAIN_WINDOW_WIDTH = 500;
  private static final int MAIN_WINDOW_HEIGHT = 400;

  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    final ResourceBundle resourceBundle = ResourceBundle.getBundle(APP_BUNDLE_NAME);
    final Window mainWindow = new Window(resourceBundle, MAIN_WINDOW_RESOURCE_NAME, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

    primaryStage.setTitle(MAIN_WINDOW_TITLE);
    mainWindow.showAt(primaryStage);
  }
}
