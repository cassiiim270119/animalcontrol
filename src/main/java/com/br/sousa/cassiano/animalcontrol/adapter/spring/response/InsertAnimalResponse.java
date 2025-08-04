package com.br.sousa.cassiano.animalcontrol.adapter.spring.response;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertAnimalResponse {
    private UUID id;
    private String name;
    private String earringId;
    private Boolean single;
    private Integer monthsOld;
    private MotherAnimalResponse mother;

    public static InsertAnimalResponse fromAnimal(final Animal animal){
        return InsertAnimalResponse.builder()
                .id(animal.getId())
                .name(animal.getName())
                .earringId(animal.getEarringId())
                .single(animal.getSingle())
                .monthsOld(animal.getMonthsOld())
                .mother(!Objects.isNull(animal.getMother()) ? MotherAnimalResponse.fromAnimal(animal.getMother()) : null)
                .build();
    }
}
