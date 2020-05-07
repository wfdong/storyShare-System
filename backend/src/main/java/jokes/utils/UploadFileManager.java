package jokes.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jokes.utils.ObjectId;
import jokes.utils.UploadFileManager;

@Component
public class UploadFileManager {

	private static Logger logger = LoggerFactory.getLogger(UploadFileManager.class);

    @Value("${jokes.upload.dir:/home/admin/static}")
    private String uploadDir;

    @PostConstruct
    private void start() {
        try {
            FileUtils.forceMkdir(new File(uploadDir));
        } catch (IOException e) {
            logger.error("ERROR ", e);
        }
    }

    public File save(String user, MultipartFile file) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        File dstFile = Paths.get(uploadDir, simpleDateFormat.format(new Date()), user + "-" + ObjectId.get().toString() + "-" + file.getOriginalFilename()).toFile();
//        File dstFile = Paths.get(uploadDir, Thread.currentThread().getName() + "-" + System.currentTimeMillis() + "-" + file.getOriginalFilename()).toFile();
        FileUtils.forceMkdir(dstFile.getParentFile());
        logger.info("Prepare save {}", dstFile);
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(dstFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return dstFile;
    }

    public File save(String user, byte[] fileBytes) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        File dstFile = Paths.get(uploadDir, simpleDateFormat.format(new Date()), user + "-" + ObjectId.get().toString() + "-" + System.currentTimeMillis() + ".jpg").toFile();
        FileUtils.forceMkdir(dstFile.getParentFile());
        logger.info("Prepare save {}", dstFile);
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(dstFile));
        out.write(fileBytes);
        out.flush();
        out.close();
        return dstFile;
    }

    public String getUploadDir() {
        return uploadDir;
    }
}
