package ru.ocupuc.smartHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ocupuc.smartHome.entity.Script;

public interface ScriptRepository extends JpaRepository<Script, Long> {
}
