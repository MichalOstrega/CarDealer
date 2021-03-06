package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Car;

import java.util.List;

public interface CarDataService {

    List<Car> loadAllAvailableCars();

    List<Car> loadAllSoldCars();

    List<Car> loadAllCars();



    Car updateCar(Car car);

    Car addCar(Car carToSave);

    Car loadCarById(Long carID);

    Car loadCarByVIN(String vin);

    void deleteCar(Long carId);
}
