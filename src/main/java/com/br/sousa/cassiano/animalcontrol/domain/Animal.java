package com.br.sousa.cassiano.animalcontrol.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    private UUID id;
    private String name;
    private String earringId;
    private Boolean single;
    private Integer monthsOld;
    private Animal mother;
    private Set<Animal> childrens;

}
