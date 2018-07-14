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
        return carRepository.findAll();
    }

    @Override
    public void addCar(Car carToSave) {
        carRepository.save(carToSave);
    }
}
