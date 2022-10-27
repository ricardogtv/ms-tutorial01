package com.moto.service.controller;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> getMotos() {

        List<Moto> motos = motoService.getAll();

        if(motos.isEmpty()) {
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMoto(@PathVariable ("id") int id) {
        Moto moto = motoService.getMotoById(id);

        if(moto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(moto);

    }

    @PostMapping
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
        Moto savedMoto = motoService.save(moto);
        return ResponseEntity.ok(savedMoto);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Moto>> getMotosByUserId(@PathVariable("userId") int id) {

        List<Moto> motos = motoService.getMotoByUserId(id);

        if(motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);

    }

}
