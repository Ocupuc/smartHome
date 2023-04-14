package ru.ocupuc.smartHome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.entity.Script;
import ru.ocupuc.smartHome.repositories.LampRepository;
import ru.ocupuc.smartHome.repositories.ScriptRepository;
import ru.ocupuc.smartHome.util.InvalidInputDataException;
import ru.ocupuc.smartHome.util.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ScriptService {

    private final ScriptRepository scriptRepository;
    private final LampRepository lampRepository;

    @Autowired
    public ScriptService(ScriptRepository scriptRepository, LampRepository lampRepository) {
        this.scriptRepository = scriptRepository;
        this.lampRepository = lampRepository;
    }

    public Script createScript(Script script) {
        return scriptRepository.save(script);
    }

    public Script updateScript(Long scriptId, Script scriptRequest) {
        return scriptRepository.findById(scriptId).map(script -> {
            script.setName(scriptRequest.getName());
            return scriptRepository.save(script);
        }).orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId));
    }

    public ResponseEntity<?> deleteScript(Long scriptId) {
        return scriptRepository.findById(scriptId).map(script -> {
            scriptRepository.delete(script);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId));
    }

    public Iterable<Script> getAllScripts() {
        return scriptRepository.findAll();
    }

    public Optional<Script> getScriptById(Long scriptId) {
        return scriptRepository.findById(scriptId);
    }

    public List<Lamp> getAllLampsByScriptId(Long scriptId) {
        return scriptRepository.findById(scriptId).orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId)).getLamps();
    }

    public String addLampToScript(Long scriptId, Long lampId) {
        Script script = scriptRepository.findById(scriptId)
                .orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId));

        Lamp lamp = lampRepository.findById(lampId)
                .orElseThrow(() -> new ResourceNotFoundException("Lamp not found with id " + lampId));

        if (script.getLamps().contains(lamp)) {
            return "Lamp already assigned to this script";
        }

        script.addLamp(lamp);
        scriptRepository.save(script);

        return "Lamp successfully assigned to script";
    }

    public String removeLampFromScript(Long scriptId, Long lampId) {
        Script script = scriptRepository.findById(scriptId)
                .orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId));

        Lamp lamp = lampRepository.findById(lampId)
                .orElseThrow(() -> new ResourceNotFoundException("Lamp not found with id " + lampId));

        if (!script.getLamps().contains(lamp)) {
            return "Lamp not assigned to this script";
        }

        script.removeLamp(lamp);
        scriptRepository.save(script);

        return "Lamp successfully removed from script";
    }

    public String moveLampInScript(Long scriptId, Long lampId, int position) {
        // Найти скрипт по ID
        Script script = scriptRepository.findById(scriptId)
                .orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + scriptId));

        // Найти лампу по ID
        Lamp lamp = lampRepository.findById(lampId)
                .orElseThrow(() -> new ResourceNotFoundException("Lamp not found with id " + lampId));

        // Убедиться, что лампа привязана к этому скрипту
        if (!script.getLamps().contains(lamp)) {
            throw new ResourceNotFoundException("Lamp is not assigned to this script");
        }

        // Получить индекс текущей позиции лампы в списке
        int currentIndex = script.getLamps().indexOf(lamp);

        // Проверить, что новая позиция в пределах допустимых значений
        if (position < 0 || position >= script.getLamps().size()) {
            throw new InvalidInputDataException("Position value is invalid");
        }

        // Если новая позиция лампы совпадает со старой, не делать ничего
        if (position == currentIndex) {
            return null;
        }

        // Удалить лампу из текущей позиции
        script.removeLamp(lamp);

        // Добавить лампу в новую позицию
        script.getLamps().add(position, lamp);

        // Сохранить изменённый скрипт в репозитории
        scriptRepository.save(script);
        return null;
    }
}