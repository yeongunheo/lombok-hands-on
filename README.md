# 테스트를 통해 배우는 롬복 라이브러리

## @FieldDefaults

> Adds modifiers to each field in the type with this annotation.
> Complete documentation is found at the project lombok features page for @FieldDefaults .
> If makeFinal is true, then each (instance) field that is not annotated with @NonFinal will have the final modifier added.
> If level is set, then each (instance) field that is package private (i.e. no access modifier) and does not have the @PackagePrivate annotation will have the appropriate access level modifier added.
  
@FieldDefaults 애노테이션을 통해 각 필드에 접근제어자(public, private, or protected)를 추가할 수 있다. 단, 필드에 직접 접근제어자가 설정되어 있으면 @FieldDefaults에서 설정한 접근제어자보다 우선하여 적용된다.

각 필드에 final 속성을 추가할 수도 있다. makeFinal 속성이 true이면 클래스 내 모든 필드가 final이 된다. 만약 makeFinal 속성이 true일 때, 특정 필드만 final을 해제하고 싶다면 @NonFinal 애노테이션을 붙이면 된다.

```jsx
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class FieldDefaultsExample {
  public final int a; //public final
  int b; //private final
  @NonFinal int c; //private final
  @PackagePrivate int d; //default final
}
```