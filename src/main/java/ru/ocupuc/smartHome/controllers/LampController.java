package ru.ocupuc.smartHome.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.repositories.LampRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lamps")
@RequiredArgsConstructor
public class LampController {
    private final LampRepository lampRepository;

    @GetMapping("")
    public List<Lamp> getAllLamps() {
        return lampRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lamp> getLampById(@PathVariable(value = "id") Long id) {
        return lampRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Lamp createLamp(@Valid @RequestBody Lamp lamp) {
        return lampRepository.save(lamp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lamp> updateLamp(@PathVariable(value = "id") Long id, @Valid @RequestBody Lamp lampDetails) {
        return lampRepository.findById(id)
                .map(lamp -> {
                    lamp.setName(lampDetails.getName());
                    lamp.setAddress(lampDetails.getAddress());
                    Lamp updatedLamp = lampRepository.save(lamp);
                    return ResponseEntity.ok(updatedLamp);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLamp(@PathVariable(value = "id") Long id) {
        return lampRepository.findById(id)
                .map(lamp -> {
                    lampRepository.delete(lamp);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}