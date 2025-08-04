package com.br.sousa.cassiano.animalcontrol.adapter.aws.ssm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetKey {

    private final SsmClient ssmClient;

    public <T> T execute(final String key, final Class<T> type) throws JsonProcessingException {
        log.info("m=GetKey.execute, status=start, parameter={}", key);
        final long initialTime = System.currentTimeMillis();
        final GetParameterRequest request = GetParameterRequest.builder()
                .name(key)
                .build();
        final GetParameterResponse response = ssmClient.getParameter(request);
        final String value = response.parameter().value();
        log.info("m=GetKey.execute, parameter={}, value={}, timeElapsed={}ms", key, value, (System.currentTimeMillis() - initialTime));

        final ObjectMapper om = new ObjectMapper();
        return om.readValue(value, type);
    }
}
