package checkspace.bundles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

// From: http://stackoverflow.com/questions/4659929/how-to-use-utf-8-in-resource-properties-with-resourcebundle
public class UTF8Control extends ResourceBundle.Control
{
  @Nullable
  public ResourceBundle newBundle(
    final String baseName,
    @NotNull final Locale locale,
    @NotNull final String format,
    final ClassLoader loader,
    final boolean reload)
    throws IllegalAccessException, InstantiationException, IOException
  {
    // The below is a copy of the default implementation.
    final String bundleName = toBundleName(baseName, locale);
    final String resourceName = toResourceName(bundleName, "properties");
    ResourceBundle bundle = null;
    InputStream stream = null;
    if (reload)
    {
      final URL url = loader.getResource(resourceName);
      if (url != null)
      {
        final URLConnection connection = url.openConnection();
        if (connection != null)
        {
          connection.setUseCaches(false);
          stream = connection.getInputStream();
        }
      }
    }
    else
    {
      stream = loader.getResourceAsStream(resourceName);
    }
    if (stream != null)
    {
      try
      {
        // Only this line is changed to make it to read properties files as UTF-8.
        bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
      } finally
      {
        stream.close();
      }
    }
    return bundle;
  }
}
