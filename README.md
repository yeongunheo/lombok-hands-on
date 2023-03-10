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

## @SuperBuilder

@Builder는 부모클래스의 필드에 적용되지 않는다. 이러한 문제를 해결하기 위해 1.18 버전부터 @SuperBuilder 애노테이션이 등장했다. 부모클래스와 자식클래스 모두 @SuperBuilder를 붙이면 된다.

```java
@Getter
@SuperBuilder
public class Parent {
    private final String parentName;
    private final int parentAge;
}

@Getter
@SuperBuilder
public class Child extends Parent {
    private final String childName;
    private final int childAge;
}

@Test
void test() {
    var student = Student.builder()
            .schoolName("학교이름")
            .childAge(13)
            .childName("aki")
            .build();
}
```

주의할 점은 @SuperBuilder는 같은 상속관계에 있는 클래스 내에서 @Builder와 함께 쓸 수 없다는 것이다. 만약 이 둘을 혼용해서 쓴다면 컴파일 에러를 마주하게 된다.

## @Jacksonized

> The @Jacksonized annotation is an add-on annotation for @Builder and @SuperBuilder . It automatically configures the generated builder class to 
> be used by Jackson's deserialization. It only has an effect if present at a context where there is also a @Builder or a @SuperBuilder; a warning 
> is emitted otherwise.

@Jacksonized 역직렬화를 위한 애노테이션이다. @Builder, @SuperBuilder와 함께 쓰인다.

클래스에 기본생성자를 만들면 역직렬화가 가능한데 굳이 @Jacksonized를 사용하는 이유는 무엇일까? 바로 불변을 보장하면서 역직렬화를 가능하게 하기 위해서이다. 아래와 같이 DTO를 불변클래스로 만들고 싶을 때 유용하게 쓸 수 있다.

```java
@Getter
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
```

## @UtilityClass

> An annotation to create utility classes. If a class is annotated with @UtilityClass, the following things happen to it:  
> - It is marked final.  
> - If any constructors are declared in it, an error is generated. Otherwise, a private no-args constructor is generated; it throws a 
  UnsupportedOperationException.  
> - All methods, inner classes, and fields in the class are marked static.  
> - WARNING: Do not use non-star static imports to import these members; javac won't be able to figure it out. Use either: import static ThisType.*; 
  or don't static-import.  

애플리케이션 전역에서 사용하는 공통 기능들은 보통 static 멤버로만 이루어진 Utility 클래스로 만들어 사용하곤 한다. 예를 들어 **StringUtils**나 **DateUtils**이 있다. 이 때 사용할 수 있는 것이 
@UtilityClass이다.

@UtilityClass는 다음과 같은 특징을 지닌다.

- 기본생성자가 private으로 생성된다.
- 인스턴스를 생성할 수 없다. 만약 인스턴스를 만들게 되면 에러가 발생한다.
- 모든 필드와 메서드에 static이 적용된다.
- final 클래스로 생성되어 상속이 불가능해진다.

