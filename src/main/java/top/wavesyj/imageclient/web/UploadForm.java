package top.wavesyj.imageclient.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
public class UploadForm {

    @FormParam("image")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public File image;

}
