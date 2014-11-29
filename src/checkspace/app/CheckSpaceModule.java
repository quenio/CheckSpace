package checkspace.app;

import checkspace.annotations.Main;
import checkspace.gui.Controller;
import checkspace.gui.IO;
import checkspace.gui.Window;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.ResourceBundle;

@Module(injects = CheckSpaceApp.class)
public class CheckSpaceModule
{
  private static final String MAIN_BUNDLE_NAME = "checkspace.bundles.CheckSpace";

  private static final String MAIN_WINDOW_RESOURCE_NAME = "MainWindow";
  private static final int MAIN_WINDOW_WIDTH = 500;
  private static final int MAIN_WINDOW_HEIGHT = 400;

  @Provides
  @Singleton
  @Main
  public ResourceBundle getMainResourceBundle()
  {
    return ResourceBundle.getBundle(MAIN_BUNDLE_NAME);
  }

  @Provides
  @Singleton
  @Main
  public Controller provideMainController()
  {
    return new MainController(new IO());
  }

  @Provides
  @Singleton
  @Main
  public Window provideMainWindow(@Main ResourceBundle resourceBundle, @Main Controller mainController)
  {
    return new Window(resourceBundle, MAIN_WINDOW_RESOURCE_NAME, mainController, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
  }

}
