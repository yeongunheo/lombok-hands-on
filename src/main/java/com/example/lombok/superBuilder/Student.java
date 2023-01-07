package com.example.lombok.superBuilder;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Student extends Child {
    private final String schoolName;
}
