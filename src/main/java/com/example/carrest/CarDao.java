package com.example.carrest;

import java.util.ArrayList;
import java.util.List;

public final class CarDao {
    private static final CarDao INSTANCE = new CarDao();
    private final List<Car> carList = new ArrayList<>();

    public static CarDao getINSTANCE() {
        return INSTANCE;
    }

    public void saveCar(Car car) {
        carList.add(car);
    }

    public void getById(int id) {
        carList.stream().filter(car -> car.getId() == id).findAny().orElseThrow();
    }

    public void deleteById(int id) {
        Car foundCar = carList.stream().filter(car -> car.getId() == id).findAny().orElseThrow();
        carList.remove(foundCar);
    }

    public List<Car> getAll() {
        return new ArrayList<>(carList);
    }

    private CarDao() {
        //
    }
}
