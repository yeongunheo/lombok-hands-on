package com.example.lombok.fieldDefaults;

import com.example.lombok.fieldDefaults.FieldDefaultsExample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FieldDefaultsExampleTest {

    @Test
    @DisplayName("필드에 직접 접근제어자가 설정되어 있으면 @FieldDefaults에서 설정한 접근제어자보다 우선하여 적용된다.")
    void test1() {
        //given
        var example = new FieldDefaultsExample();

        //then
        var a = example.a;
    }

    @Test
    @DisplayName("makeFinal=true 이면 모든 필드에 final이 적용된다. 단, 필드에 @NonFinal 키워드가 붙어 있다면 해당 필드는 final이 적용되지 않는다.")
    void test2() {
        //given
        var example = new FieldDefaultsExample();
        var expected = 10;

        //when
        example.changeC(expected);

        //then
        assertThat(example.getC()).isEqualTo(expected);
    }
}