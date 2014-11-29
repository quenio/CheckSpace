package checkspace.app;

import checkspace.gui.Window;
import dagger.ObjectGraph;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.inject.Inject;

public class CheckSpaceApp extends Application
{
  private static final String MAIN_WINDOW_TITLE = "CheckSpace";

  private final ObjectGraph objectGraph = ObjectGraph.create(new CheckSpaceModule());

  @Inject
  Window mainWindow;

  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    objectGraph.inject(this);
    primaryStage.setTitle(MAIN_WINDOW_TITLE);
    mainWindow.showAt(primaryStage);
  }
}
