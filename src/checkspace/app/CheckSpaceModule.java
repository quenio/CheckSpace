package checkspace.app;

import checkspace.analysis.FolderAnalysis;
import checkspace.analysis.FolderAnalysisController;
import checkspace.analysis.FolderAnalysisService;
import checkspace.analysis.FolderAnalysisTask;
import checkspace.bundles.UTF8Control;
import checkspace.gui.Controller;
import checkspace.gui.FXMLLayoutLoader;
import checkspace.gui.LayoutLoader;
import checkspace.gui.Window;
import checkspace.reports.UsageReportController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Module(injects = CheckSpaceApp.class)
public class CheckSpaceModule
{
  private static final String MAIN_BUNDLE_NAME = "checkspace.bundles.CheckSpace";

  private static final String MAIN_WINDOW_RESOURCE_NAME = "MainWindow";
  private static final String MAIN_WINDOW_TITLE = "CheckSpace";
  private static final int MAIN_WINDOW_WIDTH = 580;
  private static final int MAIN_WINDOW_HEIGHT = 400;

  @Provides
  @Singleton
  Window mainWindow()
  {
    return new Window(MAIN_WINDOW_RESOURCE_NAME, MAIN_WINDOW_TITLE, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
  }

  @Provides(type = Provides.Type.SET)
  @Singleton
  Controller mainController(final FolderAnalysisService folderAnalysisService)
  {
    return new MainController(folderAnalysisService);
  }

  @Provides(type = Provides.Type.SET)
  @Singleton
  Controller folderAnalysisController(
    final FolderAnalysis folderAnalysis,
    final FolderAnalysisService folderAnalysisService)
  {
    return new FolderAnalysisController(folderAnalysis, folderAnalysisService);
  }

  @Provides(type = Provides.Type.SET)
  @Singleton
  Controller usageReportController(final FolderAnalysis folderAnalysis)
  {
    return new UsageReportController(folderAnalysis);
  }

  @Provides
  @Singleton
  FolderAnalysisService folderAnalysisService(final FolderAnalysisTask.Factory taskFactory)
  {
    return new FolderAnalysisService(taskFactory);
  }

  @Provides
  @Singleton
  FolderAnalysisTask.Factory folderAnalysisTaskFactory(
    final ResourceBundle resourceBundle,
    final FolderAnalysis folderAnalysis)
  {
    return () -> new FolderAnalysisTask(resourceBundle, folderAnalysis);
  }

  @Provides
  @Singleton
  FolderAnalysis folderAnalysis()
  {
    return new FolderAnalysis();
  }

  @Provides
  @Singleton
  LayoutLoader layoutLoader(final ResourceBundle resourceBundle, final Map<Class, Controller> controllerRegistry)
  {
    return new FXMLLayoutLoader(resourceBundle, controllerRegistry);
  }

  @Provides
  @Singleton
  ResourceBundle resourceBundle(final UTF8Control utf8Control)
  {
    return ResourceBundle.getBundle(MAIN_BUNDLE_NAME, utf8Control);
  }

  @Provides
  @Singleton
  UTF8Control utf8Control()
  {
    return new UTF8Control();
  }

  @Provides
  @Singleton
  Map<Class, Controller> controllerRegistry(final Set<Controller> controllers)
  {
    final Map<Class, Controller> registry = new HashMap<>();
    for (final Controller controller : controllers)
    {
      registry.put(controller.getClass(), controller);
    }
    return registry;
  }

}
