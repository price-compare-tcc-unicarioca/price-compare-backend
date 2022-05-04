package info.merorafael.pricecompare.data.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64File implements MultipartFile {
    @NotNull
    protected final String name;

    @NotNull
    protected final byte[] file;

    @NotNull
    protected final String contentType;

    Logger logger = LoggerFactory.getLogger(Base64File.class);

    public Base64File(String name, String contentType, String base64) {
        this.name = name;
        this.file = Base64.getDecoder().decode(base64);
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.name;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return file == null || file.length == 0;
    }

    @Override
    public long getSize() {
        return file.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(file);
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
        try (var outputStream = new FileOutputStream(dest)) {
            outputStream.write(file);
        }
    }
}
