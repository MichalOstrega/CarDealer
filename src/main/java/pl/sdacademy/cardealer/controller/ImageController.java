package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/images")
public class ImageController {

    Logger logger = Logger.getLogger(getClass().getName());



    public ImageController() {
    }


    @PostMapping(value = "/upload")
    public String submit(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                String fileLocation = getFileLocation();
                String filename = fileLocation + "\\upload_" + uuid.toString();
                file.transferTo(new File(filename));
                logger.info("File has been successfully uploaded ");
            } catch (Exception e) {
                logger.info("File has not been uploaded");
            }
        } else {
            logger.info("Uploaded file is empty");
        }


        model.addAttribute("file", file);
        return "fileUploadView";
    }

    private String getFileLocation() {
        String fileLocation = new File("").getAbsolutePath();
        Path path = Paths.get(fileLocation + "\\photos");

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return path.toString();
    }


}
