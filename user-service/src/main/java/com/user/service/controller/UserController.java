package com.user.service.controller;

import com.user.service.entity.User;
import com.user.service.model.Car;
import com.user.service.model.Moto;
import com.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAll();

        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);

    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);

    }


    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);

    }

    @GetMapping("/motos/{userId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.getMotos(userId));

    }

    @PostMapping("/car/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int id, @RequestBody Car car) {
        Car savedCar = userService.saveCar(id, car);
        return ResponseEntity.ok(savedCar);

    }

    @PostMapping("/moto/{userId}")
    public ResponseEntity<Moto> saveMoto(@PathVariable("userId") int id, @RequestBody Moto moto) {
        Moto savedMoto = userService.saveMoto(id, moto);
        return ResponseEntity.ok(savedMoto);

    }

    @GetMapping("/todos/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String,Object> result =  userService.getCarsAndMotos(userId);
        return ResponseEntity.ok(result);
    }

}
