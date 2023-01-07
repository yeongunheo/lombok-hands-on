package com.example.lombok.superBuilder;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void test() {
        var student = Student.builder()
                .schoolName("학교이름")
                .childAge(13)
                .childName("aki")
                .build();
    }
}