package ru.ocupuc.smartHome.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ocupuc.smartHome.dto.LampDTO;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.repositories.LampRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LampService {

    private final LampRepository lampRepository;

    private final ScriptService scriptService;

    public Lamp create(LampDTO dto) {
        return lampRepository.save(
                Lamp.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .script(scriptService.readById(dto.getScriptId()))
                        .build());
    }

    public List<Lamp> readAll() {
        return lampRepository.findAll();
    }

    public List<Lamp> readByScriptId(Long id){
        return lampRepository.findByScriptId(id);
    }

    public Lamp update(Lamp lamp) {
        return lampRepository.save(lamp);
    }

    public void delete(Long id) {
        lampRepository.deleteById(id);
    }


}
