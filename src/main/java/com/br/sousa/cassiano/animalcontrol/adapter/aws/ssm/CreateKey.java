package com.br.sousa.cassiano.animalcontrol.adapter.aws.ssm;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.ParameterType;
import software.amazon.awssdk.services.ssm.model.PutParameterRequest;
import software.amazon.awssdk.services.ssm.model.PutParameterResponse;

@Service
@RequiredArgsConstructor
public class CreateKey {

    private final SsmClient ssmClient;
    public String execute(final Animal animal){
        PutParameterRequest parameterRequest = PutParameterRequest.builder()
                .name(animal.getName())
                .type(ParameterType.STRING)
                .value(animal.toString())
                .build();

        PutParameterResponse response = ssmClient.putParameter(parameterRequest);
        System.out.println("The parameter was successfully added.");
        return response.toString();
    }
}
