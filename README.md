# 테스트를 통해 배우는 롬복 라이브러리

## @FieldDefaults

> Adds modifiers to each field in the type with this annotation.  
> Complete documentation is found at the project lombok features page for @FieldDefaults.  
> If makeFinal is true, then each (instance) field that is not annotated with @NonFinal will have the final modifier added.  
> If level is set, then each (instance) field that is package private (i.e. no access modifier) and does not have the @PackagePrivate annotation
> will have the appropriate access level modifier added.  

@FieldDefaults 애노테이션을 통해 각 필드에 접근제어자(public, private, or protected)를 추가할 수 있다. 단, 필드에 직접 접근제어자가 설정되어 있으면 @FieldDefaults에서 설정한 접근제어자보다 우선하여 적용된다.

각 필드에 final 속성을 추가할 수도 있다. makeFinal 속성이 true이면 클래스 내 모든 필드가 final이 된다. 만약 makeFinal 속성이 true일 때, 특정 필드만 final을 해제하고 싶다면 @NonFinal 애노테이션을 붙이면 된다.

```java
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FieldDefaultsExample {
    public final int a; //public final
    int b; //private final
    @NonFinal
    int c; //private final
    @PackagePrivate
    int d; //default final
}
```

## @Builder(toBuilder = true)

빌더 패턴으로 생성된 객체의 일부 값을 변경하여 새로운 객체를 생성하고 싶을 때 toBuilder = true 속성을 사용한다. default 값은 false이다.

```java
@Getter
@Builder(toBuilder = true)
public class BuilderExample {
    private Long id;
    private String name;
}

@Test
@DisplayName("toBuilder = true를 통해 이미 빌더로 생성한 변수에 필드값을 변경할 수 있다.")
void toBuilderTest() {
    //given
    var builderExample = BuilderExample.builder()
            .id(1L)
            .name("oldName")
            .build();

    //when
    var newName = "newName";
    var newBuilderExample = builderExample.toBuilder()
            .name(newName)
            .build();

    //then
    assertThat(newBuilderExample.getName()).isEqualTo(newName);
}
```

## @Builder.Default
> If a certain field/parameter is never set during a build session, then it always gets 0 / null / false. 
> If you've put @Builder on a class (and not a method or constructor) you can instead specify the default directly on the field, and annotate the field with @Builder.Default 
> : @Builder.Default private final long created = System.currentTimeMillis();

**@Builder.Default**를 통해 기본값을 설정할 수 있다.

```java
@Builder
public class BuilderExample {
    private Long id;

    @Builder.Default
    private String name = "initName";
}

@Test
@DisplayName("@Builder.Default를 통해 기본값을 지정할 수 있다.")
void builderDefaultTest() {
    //given
    var builderExample = BuilderExample.builder()
            .build();

    //then
    assertThat(builderExample.getName()).isEqualTo("initName");
}
```

