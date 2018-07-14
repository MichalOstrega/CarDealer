package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.repository.*;

import java.util.List;

@Service
public class DefaultDictionaryService implements DictionaryService {

    private BrandRepository brandRepository;
    private CarModelRepository carModelRepository;
    private CarTypeRepository carTypeRepository;
    private FuelRepository fuelRepository;
    private ProductionYearRepository productionYearRepository;
    private TransmissionRepository transmissionRepository;

    public DefaultDictionaryService(BrandRepository brandRepository, CarModelRepository carModelRepository, CarTypeRepository carTypeRepository, FuelRepository fuelRepository, ProductionYearRepository productionYearRepository, TransmissionRepository transmissionRepository) {
        this.brandRepository = brandRepository;
        this.carModelRepository = carModelRepository;
        this.carTypeRepository = carTypeRepository;
        this.fuelRepository = fuelRepository;
        this.productionYearRepository = productionYearRepository;
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<CarType> getCarTypes() {
        return carTypeRepository.findAll();
    }

    @Override
    public List<Fuel> getFuels() {
        return fuelRepository.findAll();
    }

    @Override
    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    @Override
    public List<ProductionYear> getProductionYear() {
        return productionYearRepository.findAll();
    }

    @Override
    public List<Transmission> getTransmission() {
        return transmissionRepository.findAll();
    }
}
