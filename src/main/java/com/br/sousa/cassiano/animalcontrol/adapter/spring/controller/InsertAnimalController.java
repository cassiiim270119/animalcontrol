package com.br.sousa.cassiano.animalcontrol.adapter.spring.controller;

import com.br.sousa.cassiano.animalcontrol.adapter.aws.ssm.GetKey;
import com.br.sousa.cassiano.animalcontrol.adapter.spring.request.InsertAnimalRequest;
import com.br.sousa.cassiano.animalcontrol.adapter.spring.response.InsertAnimalResponse;
import com.br.sousa.cassiano.animalcontrol.adapter.spring.response.ResponsePattern;
import com.br.sousa.cassiano.animalcontrol.usecase.usecase.InsertAnimal;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.br.sousa.cassiano.animalcontrol.domain.util.GeneralConstants.*;

@RestController
@RequestMapping("/insert-animal")
@RequiredArgsConstructor
public class InsertAnimalController {
    private final InsertAnimal insertAnimal;
    private final GetKey getKey;

    @PostMapping("/")
    public ResponseEntity<ResponsePattern> insertAnimal(
            @RequestBody final InsertAnimalRequest request) throws JsonProcessingException {

        if (!getKey.execute(FLAG_CREATE_ANIMAL, Boolean.class)){
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(ResponsePattern
                            .cap(ERROR_UNAVAILABLE_SERVICE));
        }

        final InsertAnimalResponse response = InsertAnimalResponse
                .fromAnimal(insertAnimal.execute(request.toAnimal()));

        return ResponseEntity
                .created(URI.create("/".concat(response.getEarringId())))
                .body(ResponsePattern
                        .cap(response));
    }
}
