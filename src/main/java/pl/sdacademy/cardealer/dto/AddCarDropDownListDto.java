package pl.sdacademy.cardealer.dto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.*;

import java.util.List;

@Component
public class AddCarDropDownListDto {

    private static AddCarDropDownListDto INSTANCE = new AddCarDropDownListDto();
    List<Brand> brands;
    List<CarType> carTypes;
    List<Fuel> fuels;
    List<CarModel> carModels;
    List<ProductionYear> productionYears;
    List<Transmission> transmissions;


    private AddCarDropDownListDto() {
    }

    public static AddCarDropDownListDto getInstance(){
        return INSTANCE;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<CarType> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(List<CarType> carTypes) {
        this.carTypes = carTypes;
    }

    public List<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(List<Fuel> fuels) {
        this.fuels = fuels;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    public List<ProductionYear> getProductionYears() {
        return productionYears;
    }

    public void setProductionYears(List<ProductionYear> productionYears) {
        this.productionYears = productionYears;
    }

    public List<Transmission> getTransmissions() {
        return transmissions;
    }

    public void setTransmissions(List<Transmission> transmissions) {
        this.transmissions = transmissions;
    }
}
