package ru.ocupuc.smartHome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.repositories.LampRepository;

import java.util.List;

@Service
public class LampService {
    private final LampRepository lampRepository;

    @Autowired
    public LampService(LampRepository lampRepository) {
        this.lampRepository = lampRepository;
    }

    public List getAllLamps() {
        return lampRepository.findAll();
    }

    public Lamp getLampById(Long id) {
        return lampRepository.findById(id).orElse(null);
    }

    public Lamp addLamp(Lamp lamp) {
        return lampRepository.save(lamp);
    }

    public void deleteLamp(Long id) {
        lampRepository.deleteById(id);
    }

    public Lamp updateLamp(Long id, Lamp updatedLamp) {
        Lamp lamp = lampRepository.findById(id).orElse(null);
        if (lamp != null) {
            lamp.setName(updatedLamp.getName());
            lamp.setAddress(updatedLamp.getAddress());
            return lampRepository.save(lamp);
        }
        return null;
    }
}
