package top.wavesyj.imageclient.command;

import org.eclipse.microprofile.config.ConfigProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "config", description = "Get and set options")
public class ConfigCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    String key;

    @CommandLine.Parameters(index = "1", arity = "0..1")
    String value;

    @Override
    public void run() {
        if (value == null)
            System.out.println(ConfigProvider.getConfig().getValue(key, String.class));
    }
}
