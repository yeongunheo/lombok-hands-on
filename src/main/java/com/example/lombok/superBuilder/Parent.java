package com.example.lombok.superBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
public class Parent {
    private final String parentName;
    private final int parentAge;
}
