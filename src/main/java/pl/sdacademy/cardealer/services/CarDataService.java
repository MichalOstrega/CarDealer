package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Car;

import java.util.List;

public interface CarDataService {

    List<Car> loadAllAvailableCars();
}
