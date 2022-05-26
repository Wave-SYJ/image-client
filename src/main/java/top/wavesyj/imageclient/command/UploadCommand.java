package top.wavesyj.imageclient.command;

import io.quarkus.runtime.Quarkus;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import picocli.CommandLine;
import top.wavesyj.imageclient.web.UploadForm;
import top.wavesyj.imageclient.web.UploadService;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

@CommandLine.Command(name = "upload", description = "Upload pictures")
public class UploadCommand implements Runnable {

    @CommandLine.Parameters(arity = "1..*")
    File[] files;

    private final UploadService uploadService;

    private final String baseUrl = ConfigProvider.getConfig().getValue("server.base-url", String.class);

    public UploadCommand() {
        uploadService = RestClientBuilder.newBuilder()
                .baseUri(URI.create(baseUrl))
                .build(UploadService.class);
    }

    @Override
    public void run() {
        Uni.combine().all().unis(
                Arrays.stream(files).map(
                        file -> uploadService.postImage(new UploadForm(file))
                ).collect(Collectors.toList())
        ).combinedWith(JsonObject.class,
                responses -> responses.stream().map(response -> baseUrl + response.getString("savePath")).collect(Collectors.toList())
        ).subscribe().with(list -> {
            System.out.println("Upload Success:");
            list.forEach(System.out::println);
            Quarkus.asyncExit();
        });
        Quarkus.waitForExit();
    }
}
