package com.example.carrest;

public class CarService {

    private final CarDao carDao = CarDao.getINSTANCE();

    // create service methods and use DAO
    public void save(Car car) {
        carDao.saveCar(car);
    }
}
