package com.example.lombok.builder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuilderExample {
    Long id;

    @Builder.Default
    String name = "initName";
}
