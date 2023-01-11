package com.example.lombok.builder;

import com.example.lombok.builder.BuilderExample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BuilderExampleTest {

    @Test
    @DisplayName("toBuilder = true를 통해 이미 빌더로 생성한 변수에 필드값을 변경할 수 있다.")
    void test1() {
        //given
        var builderExample = BuilderExample.builder()
                .id(1L)
                .name("yeongun")
                .build();

        //when
        var newName = "aki";
        var newBuilderExample = builderExample.toBuilder()
                .name(newName)
                .build();

        //then
        assertThat(newBuilderExample.getName()).isEqualTo(newName);
    }

    @Test
    @DisplayName("@Builder.Default를 통해 기본값을 지정할 수 있다.")
    void test2() {
        //given
        var builderExample = BuilderExample.builder()
                .build();

        //then
        assertThat(builderExample.getName()).isEqualTo("initName");
    }
}