package com.br.sousa.cassiano.animalcontrol.adapter.spring.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePattern<T> {
    private T data;

    public static <T> ResponsePattern<T> cap(final T data) {
        return (ResponsePattern<T>) ResponsePattern.builder().data(data).build();
    }
}
