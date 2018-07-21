package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.repository.CarRepository;

import java.util.List;


@Service
public class DefaultCarDataService implements CarDataService {

    private CarRepository carRepository;

    public DefaultCarDataService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> loadAllAvailableCars() {
        return carRepository.findAllBySoldIsFalse();
    }

    @Override
    public List<Car> loadAllSoldCars() {
        return carRepository.findAllBySoldIsTrue();
    }

    @Override
    public List<Car> loadAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car addCar(Car carToSave) {
        return carRepository.save(carToSave);

    }

    @Override
    public Car loadCarById(Long carID) {
        return carRepository.findOne(carID);
    }



    @Override
    public Car loadCarByVIN(String vin) {
        return carRepository.findByVin(vin);
    }
}
