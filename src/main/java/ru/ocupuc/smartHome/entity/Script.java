package ru.ocupuc.smartHome.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scripts")
@Data
@NoArgsConstructor
public class Script {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "scripts_lamps",
            joinColumns = @JoinColumn(name = "script_id"),
            inverseJoinColumns = @JoinColumn(name = "lamp_id"))
    private List<Lamp> lamps = new ArrayList<>();

    public Script(String name) {
        this.name = name;
    }

    public void addLamp(Lamp lamp) {
        lamps.add(lamp);
        lamp.getScripts().add(this);
    }

    public void removeLamp(Lamp lamp) {
        lamps.remove(lamp);
        lamp.getScripts().remove(this);
    }
}