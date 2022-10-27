package com.car.service.service;

import com.car.service.entity.Car;
import com.car.service.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){

        this.carRepository = carRepository;

    }


    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);

    }

    public Car save(Car car){
        return carRepository.save(car);

    }

    public List<Car> getCarByUserId(int userId) {
        return carRepository.findByUserId(userId);

    }


}
