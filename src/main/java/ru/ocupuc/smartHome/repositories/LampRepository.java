package ru.ocupuc.smartHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ocupuc.smartHome.entity.Lamp;

import java.util.List;

@Repository
public interface LampRepository extends JpaRepository<Lamp, Long> {
    List<Lamp> findByScriptId(Long id);
}
