package pl.sdacademy.cardealer.model;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(name = "photos")
public class Photo extends BaseModel {

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "image_path")
    private String imagePath;

    @Column
    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Photo() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
