package com.car.service.controller;

import com.car.service.entity.Car;
import com.car.service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {

        List<Car> cars = carService.getAll();

        if(cars.isEmpty()) {
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable ("id") int id) {
        Car car = carService.getCarById(id);

        if(car == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(car);

    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car savedCar = carService.save(car);
        return ResponseEntity.ok(savedCar);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int id) {

        List<Car> cars = carService.getCarByUserId(id);

        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);

    }


}
