package checkspace;

import checkspace.gui.Window;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class CheckSpace extends Application
{
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
    primaryStage.setTitle(MAIN_WINDOW_TITLE);

    final ResourceBundle resourceBundle = ResourceBundle.getBundle("checkspace.bundles.CheckSpace");
    final Window mainWindow = new Window(resourceBundle, MAIN_WINDOW_RESOURCE_NAME, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

    mainWindow.showAt(primaryStage);
  }
}
