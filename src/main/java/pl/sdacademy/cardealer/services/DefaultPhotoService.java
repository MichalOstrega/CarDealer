package pl.sdacademy.cardealer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Photo;
import pl.sdacademy.cardealer.repository.PhotoRepository;

@Service
public class DefaultPhotoService implements PhotoService {

    PhotoRepository photoRepository;

    @Autowired
    public DefaultPhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo savePhoto(Photo photo) {

        return photoRepository.save(photo);
    }
}
