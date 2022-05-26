package top.wavesyj.imageclient.command;

import org.eclipse.microprofile.config.ConfigProvider;
import picocli.CommandLine;

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

@CommandLine.Command(name = "config", description = "Get and set options", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    String key;

    @CommandLine.Parameters(index = "1", arity = "0..1")
    String value;

    @Override
    public void run() {
        if (value == null) {
            System.out.println(ConfigProvider.getConfig().getValue(key, String.class));
            return;
        }

        String dir = new File(ConfigCommand.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        String path = Path.of(dir, "config.properties").toString();

        Properties prop = new Properties();

        try (InputStream inputStream = new FileInputStream(path)) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prop.setProperty(key, value);

        try (OutputStream outputStream = new FileOutputStream(path)) {
            prop.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
