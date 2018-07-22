package pl.sdacademy.cardealer.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.repository.*;

import java.util.List;
import java.util.Set;

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
        return brandRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public List<CarType> getCarTypes() {
        return carTypeRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public List<Fuel> getFuels() {
        return fuelRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public List<CarModel> getCarModels() {
        return carModelRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public List<ProductionYear> getProductionYear() {
        return productionYearRepository.findAll(new Sort(Sort.Direction.DESC,"name"));
    }

    @Override
    public List<Transmission> getTransmission() {
        return transmissionRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<CarModel> addModel(List<CarModel> collect) {
        return carModelRepository.save(collect);
    }

    @Override
    public CarModel addModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @Override
    public List<CarModel> getCarModels(Long brandId) {
        return carModelRepository.findByBrand_IdOrderByName(brandId);
    }


}
