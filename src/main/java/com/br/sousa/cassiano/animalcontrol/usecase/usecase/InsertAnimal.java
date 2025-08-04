package com.br.sousa.cassiano.animalcontrol.usecase.usecase;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
public class InsertAnimal {

    public Animal execute(final Animal animal) {
        final long initialTime = System.currentTimeMillis();
        log.info("m=InsertAnimal.execute, status=start, animalName={}", animal.getName());
        animal.setId(UUID.randomUUID());
        animal.setMother(Animal.builder()
                .id(UUID.randomUUID())
                .earringId("1321564")
                .name("Mamãe da " + animal.getName())
                .single(false)
                .monthsOld(50)
                .mother(new Animal())
                .childrens(new HashSet<>(List.of(animal)))
                .build());
        animal.setChildrens(new HashSet<>(List.of(Animal.builder()
                .id(UUID.randomUUID())
                .earringId("13215642")
                .name("Filha da " + animal.getName())
                .single(false)
                .monthsOld(5)
                .childrens(new HashSet<>(List.of(new Animal())))
                .build())));
        log.info("m=InsertAnimal.execute, status=end, timeElapsed={}ms", (System.currentTimeMillis() - initialTime));
        return animal;
    }
}
