package ru.ocupuc.smartHome.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lamp {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String address;



}

