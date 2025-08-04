package com.br.sousa.cassiano.animalcontrol.adapter.spring.request;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertAnimalRequest {

    private String name;
    private String earringId;
    private Boolean single;
    private Integer monthsOld;
    private String motherId;

    public Animal toAnimal(){
        return Animal.builder()
                .name(name)
                .earringId(earringId)
                .single(single)
                .monthsOld(monthsOld)
                .mother(motherId == null ? null : Animal.builder().id(UUID.fromString(motherId)).build())
                .build();
    }
}
