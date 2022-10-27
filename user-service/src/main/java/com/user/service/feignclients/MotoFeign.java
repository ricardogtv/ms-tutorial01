package com.user.service.feignclients;

import com.user.service.model.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeign {

    @PostMapping()
    public Moto saveMoto(@RequestBody Moto moto);

    @GetMapping("/user/{userId}")
    public List<Moto> getMotosByUserId(@PathVariable("userId") int id);

}
