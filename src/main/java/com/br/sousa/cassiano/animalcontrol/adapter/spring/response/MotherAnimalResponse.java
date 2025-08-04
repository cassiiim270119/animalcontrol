package com.br.sousa.cassiano.animalcontrol.adapter.spring.response;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotherAnimalResponse {
    private UUID id;
    private String name;
    private String earringId;
    private Boolean single;
    private Integer monthsOld;

    public static MotherAnimalResponse fromAnimal(final Animal animal){
        return MotherAnimalResponse.builder()
                .id(animal.getId())
                .name(animal.getName())
                .earringId(animal.getEarringId())
                .single(animal.getSingle())
                .monthsOld(animal.getMonthsOld())
                .build();
    }
}
