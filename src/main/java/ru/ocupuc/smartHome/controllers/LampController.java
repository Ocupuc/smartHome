package ru.ocupuc.smartHome.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.repositories.LampRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lamps")
public class LampController {
    private final LampRepository lampRepository;

    public LampController(LampRepository lampRepository) {
        this.lampRepository = lampRepository;
    }

    @GetMapping("")
    public List<Lamp> getAllLamps() {
        return lampRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lamp> getLampById(@PathVariable(value = "id") Long id) {
        Optional<Lamp> lamp = lampRepository.findById(id);
        if (lamp.isPresent()) {
            return ResponseEntity.ok().body(lamp.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public Lamp createLamp(@Valid @RequestBody Lamp lamp) {
        return lampRepository.save(lamp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lamp> updateLamp(@PathVariable(value = "id") Long id, @Valid @RequestBody Lamp lampDetails) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        if (optionalLamp.isPresent()) {
            Lamp lamp = optionalLamp.get();
            lamp.setName(lampDetails.getName());
            lamp.setAddress(lampDetails.getAddress());
            Lamp updatedLamp = lampRepository.save(lamp);
            return ResponseEntity.ok(updatedLamp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lamp> deleteLamp(@PathVariable(value = "id") Long id) {
        Optional<Lamp> optionalLamp = lampRepository.findById(id);
        if (optionalLamp.isPresent()) {
            lampRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
