package ru.ocupuc.smartHome.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ocupuc.smartHome.entity.Script;

@Repository
public interface ScriptRepository extends CrudRepository<Script, Long> {
}