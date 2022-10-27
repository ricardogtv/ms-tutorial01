package com.user.service.service;

import com.user.service.entity.User;
import com.user.service.feignclients.CarFeign;
import com.user.service.feignclients.MotoFeign;
import com.user.service.model.Car;
import com.user.service.model.Moto;
import com.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CarFeign carFeign;
    private final MotoFeign motoFeign;

    public UserService(UserRepository userRepository, RestTemplate restTemplate, CarFeign carFeign, MotoFeign motoFeign){
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.carFeign = carFeign;
        this.motoFeign = motoFeign;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);

    }

    public User save(User user){
        return userRepository.save(user);

    }

    public List<Car> getCars(int userId) {

        List<Car> cars = restTemplate
                .getForObject("http://localhost:8002/car/user/" + userId, List.class);
        return cars;

    }

    public List<Moto> getMotos(int userId) {

        List<Moto> motos = restTemplate
                .getForObject("http://localhost:8003/moto/user/" + userId, List.class);
        return motos;

    }

    public Car saveCar(int userId, Car car) {

        car.setUserId(userId);
        Car savedCar = carFeign.saveCar(car);

        return savedCar;

    }

    public Moto saveMoto(int userId, Moto moto) {

        moto.setUserId(userId);
        Moto savedMoto = motoFeign.saveMoto(moto);

        return savedMoto;

    }


    public Map<String, Object> getCarsAndMotos(int usuarioId){
        Map<String,Object> resultado = new HashMap<>();
        User usuario = userRepository.findById(usuarioId).orElse(null);

        if(usuario == null) {
            resultado.put("Warning - Info", "User does not exist");
            return resultado;
        }

        resultado.put("User",usuario);
        List<Car> carros = carFeign.getCarsByUserId(usuarioId);
        if(carros.isEmpty()) {
            resultado.put("Cars", "User does not have Cars");
        }
        else {
            resultado.put("Cars", carros);
        }

        List<Moto> motos = motoFeign.getMotosByUserId(usuarioId);
        if(motos.isEmpty()) {
            resultado.put("Motos", "User does not have Motos");
        }
        else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }


}
