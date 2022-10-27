package com.moto.service.service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {

        this.motoRepository = motoRepository;

    }

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);

    }

    public Moto save(Moto moto){
        return motoRepository.save(moto);

    }

    public List<Moto> getMotoByUserId(int userId) {
        return motoRepository.findMotoByUserId(userId);

    }

}
