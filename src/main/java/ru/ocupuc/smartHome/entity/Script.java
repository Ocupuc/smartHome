package ru.ocupuc.smartHome.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "script")
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "script", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lamp> lamps = new ArrayList<>();

    // конструкторы, геттеры и сеттеры

    public Script() {}

    public Script(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lamp> getLamps() {
        return lamps;
    }

    public void setLamps(List<Lamp> lamps) {
        this.lamps = lamps;
    }

    public void addLamp(Lamp lamp) {
        lamps.add(lamp);
        lamp.setScript(this);
    }

    public void removeLamp(Lamp lamp) {
        lamps.remove(lamp);
        lamp.setScript(null);
    }
}
