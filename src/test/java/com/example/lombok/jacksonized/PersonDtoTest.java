package com.example.lombok.jacksonized;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonDtoTest {

    @Test
    @DisplayName("@Jacksonized를 @Builder와 함께 사용하면 역직렬화가 가능하다.")
    void testJson() throws JsonProcessingException {
        PersonDto personDto = PersonDto.builder()
                .name("Tom")
                .age(18)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(personDto);
        System.out.println("personDto Json:: " + json);

        PersonDto dto = objectMapper.readValue(json, PersonDto.class); //역직렬화시 에러 발생, 기본생성자를 만들면 역직렬화가 성공하지만 불변이 깨진다는 단점이 있다.
        //불변을 보장하면서 역직렬화를 가능하게 하기 위해 사용하는것이 바로 @Jacksonized 이다.
        System.out.println("personDto = " + dto);

        assertThat(personDto).isEqualTo(dto);
    }
}