package top.wavesyj.imageclient.config;

import io.quarkus.runtime.configuration.ApplicationPropertiesConfigSourceLoader;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;
import top.wavesyj.imageclient.command.ConfigCommand;

import java.io.File;
import java.nio.file.Path;


public class FileConfigSource extends ApplicationPropertiesConfigSourceLoader implements ConfigSourceProvider {

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader classLoader) {
        String dir = new File(ConfigCommand.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        String path = Path.of(dir, "config.properties").toString();

        return loadConfigSources(path, 270, classLoader);
    }
}
