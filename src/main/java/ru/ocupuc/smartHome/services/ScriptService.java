package ru.ocupuc.smartHome.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ocupuc.smartHome.dto.ScriptDTO;
import ru.ocupuc.smartHome.entity.Script;
import ru.ocupuc.smartHome.repositories.ScriptRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ScriptService {

    private final ScriptRepository scriptRepository;

    public List<Script> readAll(){
        return scriptRepository.findAll();
    }

    public Script readById(Long id){
        return scriptRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Script not found - " + id));
    }
    public Script create(ScriptDTO dto) {
        return scriptRepository.save(
                Script.builder()
                        .name(dto.getName())
                        .build());
    }
}
