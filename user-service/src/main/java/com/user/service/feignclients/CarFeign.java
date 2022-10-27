package com.user.service.feignclients;

import com.user.service.model.Car;
import com.user.service.model.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002")
@RequestMapping("/car")
public interface CarFeign {

    @PostMapping()
    public Car saveCar(@RequestBody Car car);

    @GetMapping ("/user/{userId}")
    public List<Car> getCarsByUserId(@PathVariable ("userId") int id);

}
