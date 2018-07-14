package pl.sdacademy.cardealer.model;


import javax.persistence.*;

@MappedSuperclass
public abstract class BaseModelVersion extends BaseModel {

    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
