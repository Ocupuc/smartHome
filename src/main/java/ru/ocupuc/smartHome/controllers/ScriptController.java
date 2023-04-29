package ru.ocupuc.smartHome.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.smartHome.dto.ScriptDTO;
import ru.ocupuc.smartHome.entity.Script;
import ru.ocupuc.smartHome.services.ScriptService;

import java.util.List;

@RestController
@RequestMapping("/scripts")
@AllArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<List<Script>> readAll(){
        return new ResponseEntity<>(scriptService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Script> create(@RequestBody ScriptDTO dto){
        return new ResponseEntity<>(scriptService.create(dto), HttpStatus.OK);
    }

}
