package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.cardealer.model.Photo;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.PhotoService;

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
public class PhotoController {

    Logger logger = Logger.getLogger(getClass().getName());
    CarDataService carDataService;
    PhotoService photoService;


    public PhotoController(CarDataService carDataService, PhotoService photoService) {
        this.carDataService = carDataService;
        this.photoService = photoService;
    }

    @Transactional
    @PostMapping(value = "/upload")
    public String submit(@RequestParam("file") MultipartFile file, @RequestParam("carId") Long carId, Model model) {
        if (!file.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                String fileLocation = getFileLocation();
                String imagePath = "/img/upload_" + uuid.toString();
                String imageFile = fileLocation + imagePath;
                file.transferTo(new File(imageFile));
                Photo photo = new Photo();
                photo.setCar(carDataService.loadCarById(carId));
                photo.setContentType(file.getContentType());
                photo.setSize(file.getSize());
                photo.setImagePath(imagePath);
                photoService.savePhoto(photo);


            } catch (Exception e) {
            }
        }


        return "redirect:/cars/" + carId;
    }

    private String getFileLocation() {
        String fileLocation = new File("").getAbsolutePath();
        Path path = Paths.get(fileLocation + "/src/main/resources/static/");

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
