package ru.ocupuc.smartHome.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.smartHome.dto.LampDTO;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.services.LampService;

import java.util.List;

@RestController
@RequestMapping("/lamps")
@AllArgsConstructor
public class LampController {

    private final LampService lampService;

    @PostMapping
    public ResponseEntity<Lamp> create(@RequestBody LampDTO dto){
        return new ResponseEntity<>(lampService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Lamp>> readAll(){
        return new ResponseEntity<>(lampService.readAll(),HttpStatus.OK);
    }

    @GetMapping("/script/{id}")
    public ResponseEntity<List<Lamp>> readByScriptId(@PathVariable Long id){
        return new ResponseEntity<>(lampService.readByScriptId(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Lamp> update(@RequestBody Lamp lamp){
        return new ResponseEntity<>(lampService.update(lamp),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        lampService.delete(id);
        return HttpStatus.OK;
    }

}