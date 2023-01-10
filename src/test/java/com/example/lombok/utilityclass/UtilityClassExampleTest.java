package com.example.lombok.utilityclass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UtilityClassExampleTest {

    @Test
    @DisplayName("@UtilityClass는 인스턴스를 만들 수 없다.")
    void test1() {
//        var utilityClassExample = new UtilityClassExample();
    }

    @Test
    @DisplayName("@UtilityClass는 모든 필드와 메서드에 static이 적용된다.")
    void test2() {
        //when
        var result = UtilityClassExample.addSomething(1);

        //then
        assertThat(result).isEqualTo(6);
    }
}