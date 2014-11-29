package checkspace.app;

import checkspace.analysis.FolderAnalysisEventHandler;
import checkspace.analysis.FolderAnalysisService;
import checkspace.analysis.FolderAnalysisTask;
import checkspace.bundles.UTF8Control;
import checkspace.gui.Controller;
import checkspace.gui.FXMLLayoutLoader;
import checkspace.gui.LayoutLoader;
import checkspace.gui.Window;
import checkspace.reports.IO;
import checkspace.reports.UsageReport;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.ResourceBundle;

@Module(injects = CheckSpaceApp.class)
public class CheckSpaceModule
{
  private static final String MAIN_BUNDLE_NAME = "checkspace.bundles.CheckSpace";

  private static final String MAIN_WINDOW_RESOURCE_NAME = "MainWindow";
  private static final String MAIN_WINDOW_TITLE = "CheckSpace";
  private static final int MAIN_WINDOW_WIDTH = 500;
  private static final int MAIN_WINDOW_HEIGHT = 400;

  @Provides
  @Singleton
  Window mainWindow()
  {
    return new Window(MAIN_WINDOW_RESOURCE_NAME, MAIN_WINDOW_TITLE, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
  }

  @Provides
  @Singleton
  Controller mainController(
    final FolderAnalysisService folderAnalysisService,
    final FolderAnalysisEventHandler folderAnalysisEventHandler)
  {
    return new MainController(folderAnalysisService, folderAnalysisEventHandler);
  }

  @Provides
  @Singleton
  FolderAnalysisService folderAnalysisService(final FolderAnalysisTask.Factory taskFactory)
  {
    return new FolderAnalysisService(taskFactory);
  }

  @Provides
  @Singleton
  FolderAnalysisTask.Factory folderAnalysisTaskFactory(final ResourceBundle resourceBundle)
  {
    return path -> new FolderAnalysisTask(resourceBundle, path);
  }

  @Provides
  @Singleton
  FolderAnalysisEventHandler folderAnalysisEventHandler(final UsageReport usageReport)
  {
    return new FolderAnalysisEventHandler(usageReport);
  }

  @Provides
  @Singleton
  UsageReport usageReport(final IO io)
  {
    return new UsageReport(io);
  }

  @Provides
  @Singleton
  IO io()
  {
    return new IO();
  }

  @Provides
  @Singleton
  LayoutLoader layoutLoader(final ResourceBundle resourceBundle)
  {
    return new FXMLLayoutLoader(resourceBundle);
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
}
