package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.*;

import java.util.List;

public interface DictionaryService {
    List<Brand> getBrands();
    List<CarType> getCarTypes();
    List<Fuel> getFuels();
    List<CarModel> getCarModels();
    List<ProductionYear> getProductionYear();
    List<Transmission> getTransmission();

}
