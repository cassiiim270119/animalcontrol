package com.br.sousa.cassiano.animalcontrol.usecase.usecase;

import com.br.sousa.cassiano.animalcontrol.domain.Animal;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;

import java.util.*;

import static com.br.sousa.cassiano.animalcontrol.domain.util.GeneralConstants.OPENAI_KEY;


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

        OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(OPENAI_KEY).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage("Me responda esse teste")
                .model(ChatModel.O3_MINI)
                .build();
        ChatCompletion chatCompletion = client.chat().completions().create(params);

        log.info("m=InsertAnimal.execute, status=openaiResponse, response={}", chatCompletion.toString());
        log.info("m=InsertAnimal.execute, status=openaiResponse2, response={}", chatCompletion.choices().get(0).message().content());

        return animal;
    }
}
