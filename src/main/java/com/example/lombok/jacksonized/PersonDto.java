package com.example.lombok.jacksonized;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@EqualsAndHashCode
public class PersonDto {
    private final String name;
    private final int age;

    @Jacksonized
    @Builder
    private PersonDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
