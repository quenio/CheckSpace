package checkspace.app;

import checkspace.analysis.FolderAnalysisEventHandler;
import checkspace.analysis.FolderAnalysisService;
import checkspace.gui.Controller;
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
  private static final int MAIN_WINDOW_WIDTH = 500;
  private static final int MAIN_WINDOW_HEIGHT = 400;

  @Provides
  @Singleton
  Window mainWindow(ResourceBundle resourceBundle, Controller mainController)
  {
    return new Window(resourceBundle, MAIN_WINDOW_RESOURCE_NAME, mainController, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
  }

  @Provides
  @Singleton
  ResourceBundle mainResourceBundle()
  {
    return ResourceBundle.getBundle(MAIN_BUNDLE_NAME);
  }

  @Provides
  @Singleton
  Controller mainController(FolderAnalysisService folderAnalysisService, FolderAnalysisEventHandler folderAnalysisEventHandler)
  {
    return new MainController(folderAnalysisService, folderAnalysisEventHandler);
  }

  @Provides
  @Singleton
  FolderAnalysisService folderAnalysisService()
  {
    return new FolderAnalysisService();
  }

  @Provides
  @Singleton
  FolderAnalysisEventHandler folderAnalysisEventHandler(UsageReport usageReport, FolderAnalysisService folderAnalysisService)
  {
    return new FolderAnalysisEventHandler(usageReport, folderAnalysisService);
  }

  @Provides
  @Singleton
  UsageReport usageReport(IO io)
  {
    return new UsageReport(io);
  }

  @Provides
  @Singleton
  IO io()
  {
    return new IO();
  }
}
