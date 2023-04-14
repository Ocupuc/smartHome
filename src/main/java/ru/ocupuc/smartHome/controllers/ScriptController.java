package ru.ocupuc.smartHome.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.smartHome.entity.Script;
import ru.ocupuc.smartHome.repositories.ScriptRepository;
import ru.ocupuc.smartHome.services.ScriptService;
import ru.ocupuc.smartHome.util.ResourceNotFoundException;

import java.util.List;

@Controller
@RequestMapping("/")
public class ScriptController {

    private final ScriptService scriptService;
    private final ScriptRepository scriptRepository;

    @Autowired
    public ScriptController(ScriptService scriptService,
                            ScriptRepository scriptRepository) {
        this.scriptService = scriptService;
        this.scriptRepository = scriptRepository;
    }

    @GetMapping("/scripts")
    public String getScripts(Model model) {
        List<Script> scripts = (List<Script>) scriptService.getAllScripts();
        model.addAttribute("scripts", scripts);
        return "scripts";
    }


    @GetMapping("/scripts/{id}")
    public String getScriptById(Model model, @PathVariable Long id) {
        Script script = scriptService.getScriptById(id).orElseThrow(() -> new ResourceNotFoundException("Script not found with id " + id));
        model.addAttribute("script", script);

        return "scriptDetails";
    }


    @GetMapping("/scripts/scriptForm")
    public String showScriptForm(Model model) {
        model.addAttribute("script", new Script());
        return "script-form";
    }

    @PostMapping("/scripts/saveScript")
    public String saveScript(@ModelAttribute("script") Script script) {
        scriptService.createScript(script);
        return "redirect:/scripts";
    }

    @PostMapping("/scripts/delete/{id}")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Script script = scriptRepository.findById(id).orElseThrow();
        scriptRepository.delete(script);
        return "redirect:/scripts";
    }
}