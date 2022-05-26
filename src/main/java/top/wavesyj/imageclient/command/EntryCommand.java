package top.wavesyj.imageclient.command;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(
        name = "imgcli",
        mixinStandardHelpOptions = true,
        subcommands = {ConfigCommand.class, UploadCommand.class},
        version = "image-client 0.1")
public class EntryCommand {
}
