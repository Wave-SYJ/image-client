package top.wavesyj.imageclient.config;

import io.quarkus.runtime.configuration.ApplicationPropertiesConfigSourceLoader;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;


public class FileConfigSource extends ApplicationPropertiesConfigSourceLoader implements ConfigSourceProvider {

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader classLoader) {
        return loadConfigSources("config.properties", 270, classLoader);
    }
}
