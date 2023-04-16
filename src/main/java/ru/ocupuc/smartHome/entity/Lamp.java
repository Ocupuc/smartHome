package ru.ocupuc.smartHome.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lamps")
@Data
@NoArgsConstructor
public class Lamp {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "lamps")
    private List<Script> scripts = new ArrayList<>();



    public Lamp(String name) {
        this.name = name;
    }

}

