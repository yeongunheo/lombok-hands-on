package com.example.lombok.superBuilder;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Child extends Parent {
    private final String childName;
    private final int childAge;
}
