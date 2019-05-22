package SIIT.Final.Project.service;

import SIIT.Final.Project.model.Trip;
import SIIT.Final.Project.repository.FileUploadRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileUploadServ {

    private static String UPLOAD_ROOT = "upload-dir";

    public final FileUploadRep fileUploadRep;
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadServ(FileUploadRep fileUploadRep, ResourceLoader resourceLoader) {

        this.fileUploadRep = fileUploadRep;
        this.resourceLoader = resourceLoader;
    }

    public Resource findOneImage(String filename) {

        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename);
    }

    public void createImage(MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            fileUploadRep.save(new Trip(file.getOriginalFilename()));
        }
    }

    public void deleteImage(String filename) throws IOException{

        final Trip byName = fileUploadRep.findByName(filename);
        fileUploadRep.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
    }

    @Bean
    CommandLineRunner setUp(FileUploadRep fileUploadRep) throws IOException{
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));

            Files.createDirectory(Paths.get(UPLOAD_ROOT));

            FileCopyUtils.copy("Test file", new FileWriter(UPLOAD_ROOT + "/test"));
            fileUploadRep.save(new Trip("test"));

            FileCopyUtils.copy("Test file2", new FileWriter(UPLOAD_ROOT + "/test2"));
            fileUploadRep.save(new Trip("test2"));

        };
    }
}
