package SIIT.Final.Project.controller;

import SIIT.Final.Project.service.FileUploadServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileUpload {

    private static final String BASE_PATH = "/images";
    private static final String FILENAME = "{filename:.+}";

    private final FileUploadServ fileUploadServ;

    @Autowired
    public FileUpload(FileUploadServ fileUploadServ) {

        this.fileUploadServ = fileUploadServ;
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/" + FILENAME + "/raw")
    @ResponseBody
    public ResponseEntity<?> oneRawImage(@PathVariable String filename) {

        try {
            Resource file = fileUploadServ.findOneImage(filename);
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Couldn't find " + filename + " => " + e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH)
    public String createFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            fileUploadServ.createImage(file);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded " + file.getName());
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload " + file.getName() + " => " + e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = BASE_PATH + "/" + FILENAME)
    public String deleteFile(@PathVariable String filename,
                             RedirectAttributes redirectAttributes) {
        try {
            fileUploadServ.deleteImage(filename);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully deleted " + filename);
        } catch (IOException|RuntimeException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to delete " + filename + " => " + e.getMessage());
        }
        return "redirect:/";
    }

}
