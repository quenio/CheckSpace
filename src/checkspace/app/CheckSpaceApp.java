package checkspace.app;

import checkspace.gui.Controller;
import checkspace.gui.LayoutLoader;
import checkspace.gui.Window;
import dagger.ObjectGraph;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.inject.Inject;

public class CheckSpaceApp extends Application
{
  private final ObjectGraph objectGraph = ObjectGraph.create(new CheckSpaceModule());

  @Inject
  Window mainWindow;

  @Inject
  Controller mainController;

  @Inject
  LayoutLoader layoutLoader;

  public static void main(final String[] args)
  {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception
  {
    objectGraph.inject(this);
    mainWindow.loadLayout(layoutLoader, mainController);
    mainWindow.showOn(primaryStage);
  }
}
