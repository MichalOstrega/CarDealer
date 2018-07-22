package pl.sdacademy.cardealer.services;

import org.springframework.ui.Model;
import pl.sdacademy.cardealer.model.*;

import java.util.List;
import java.util.Set;

public interface DictionaryService {
    List<Brand> getBrands();
    List<CarType> getCarTypes();
    List<Fuel> getFuels();
    List<CarModel> getCarModels();
    List<ProductionYear> getProductionYear();
    List<Transmission> getTransmission();

    Brand addBrand(Brand brand);


    List<CarModel> getCarModels(Long brandId);
    List<CarModel> addModel(List<CarModel> collect);
    CarModel addModel(CarModel carModel);
}
