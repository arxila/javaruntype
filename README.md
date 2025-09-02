JavaRuntype
===========

JavaRuntype is a compact Java library that provides a runtime representation of the Java type system. It lets 
you model, build, inspect and compare Java types (including generics, bounds and arrays) at runtime in a 
convenient, type-safe way.

This is particularly useful for metadata-driven systems and frameworks, where you need to reason about types
beyond raw classes, and you want to include generics semantics. Java’s `Class` cannot carry generic parameters
at runtime, and while `java.lang.reflect.Type` offers the JVM view of type signatures, it is not ergonomic for
use as a first-class metadata model. JavaRuntype fills that gap with a more practical API.

All `Type` and `TypeDef` objects are <ins>**immutable**</ins>, and therefore <ins>**thread-safe**</ins>. They
are also **serializable**. All caches in the library are **thread-safe** and **non-blocking**.

JavaRuntype is open-source and distributed under the **Apache License 2.0**.

> [!NOTE]
> This library is a new incarnation of the previous `org.javaruntype` library which could be previously found
> at [javaruntype](https://github.com/javaruntype/javaruntype). This new library starts at version 2.x, it is a
> complete rewrite of the previous one, and it is not backward compatible.



Requirements
------------

JavaRuntype requires **Java 17**.



Maven Info
----------

Library dependency: `io.arxila.javaruntype:javaruntype:{version}`

From Maven:

```xml
<dependency>
  <groupId>io.arxila.javaruntype</groupId>
  <artifactId>javaruntype</artifactId>
  <version>{version}</version>
</dependency>
```



The Type System
---------------

JavaRuntype models the Java type system through two primary abstractions:

- **Type**: an instantiated, fully resolved type (optionally with array dimensions). Examples: `List<String>`, 
  `List<? extends Serializable>`, `Map<String, List<Integer>>`, `String[][]`.
- **TypeDef**: a type definition blueprint derived from a class declaration (with type variables and bounds intact). 
  Examples: `List<E>`, `Map<K,V>`, `LinkedHashMap<K,V>`.

Types are immutable and therefore thread-safe. They can be compared, serialized and used to determine assignability
with precise _generics_ semantics.

### Type

Class: `io.arxila.javaruntype.type.Type`

A `Type` object represents an instantiation of a Type Definition. For example:

- `java.util.List<java.lang.String>` is an instantiation of the `List` Type Definition, resolving the `E` type
  parameter as the `java.lang.String` Type.
- `java.util.List<? extends java.io.Serializable>` is another instantiation of the `List` Type Definition, this
  time resolving the `E` type parameter as an upper bound with type `java.io.Serializable`.

`Type` objects are mainly obtained through the `Types` class (`io.arxila.javaruntype.type.Types`), and with the help
of the `TypeParameters` (`io.arxila.javaruntype.type.TypeParameters`) class:

```java
//
// "Types.listOf(...)" is a static utility method in Types.
// "Types.STRING" is a static constant for the java.lang.String Type.
//
// listOfStringType = "java.util.List<java.lang.String>"
//
Type<List<String>> listOfStringType = Types.listOf(Types.STRING);

// For this type, we need to create an upper bound
Type<List<? extends Serializable>> listOfUnknownExtSerializableType =
        Types.listOf(TypeParameters.forExtendsType(Types.SERIALIZABLE));

// List<? extends Serializable> is assignable from List<String>
assertTrue(listOfUnknownExtSerializableType.isAssignableFrom(listOfStringType));
```


### TypeDef

Class: `io.arxila.javaruntype.typedef.TypeDef`

A `TypeDef` object represents the declaration of a class or interface—the blueprint on which `Type` objects
are created and validated. It captures _generics_ variable names and bounds as declared.

Some examples of `TypeDef`s:

- `java.util.List<E>` is the TypeDef for `java.util.List`.
- `java.util.LinkedHashMap<K,V>` is the TypeDef for `java.util.LinkedHashMap`.

TypeDefs are mainly obtained via `TypeDefs` (`io.arxila.javaruntype.typedef.TypeDefs`):

```java
// stringTypeDef = "java.lang.String"
TypeDef stringTypeDef = TypeDefs.forClass(String.class);

// listTypeDef = "java.util.List<E>"
TypeDef listTypeDef = TypeDefs.forClass(List.class);
```


### Getting Type Objects

JavaRuntype provides several ways to obtain `Type` instances.

#### 1) Static constants

`Types` (`io.arxila.javaruntype.type.Types`) includes a comprehensive set of predefined constants for common types.

| Java Type                       | Constant                       |
|:--------------------------------|:-------------------------------|
| `java.lang.String`              | `Types.STRING`                 |
| `java.lang.Integer`             | `Types.INTEGER`                |
| `java.util.Calendar`            | `Types.CALENDAR`               |
| `java.io.Serializable`          | `Types.SERIALIZABLE`           |
| `java.lang.Long[]`              | `Types.ARRAY_OF_LONG`          |
| `java.util.List<?>`             | `Types.LIST_OF_UNKNOWN`        |
| `java.util.List<String>`        | `Types.LIST_OF_STRING`         |
| `java.util.Set<Calendar>`       | `Types.SET_OF_CALENDAR`        |
| `java.util.Map<?,?>`            | `Types.MAP_OF_UNKNOWN_UNKNOWN` |
| `java.util.Map<String,String>`  | `Types.MAP_OF_STRING_STRING`   |
| `java.util.Map<String,Boolean>` | `Types.MAP_OF_STRING_BOOLEAN`  |

…and many more.

#### 2) From class

Raw type from class:

```java
Type<String> strType = Types.forClass(String.class);
// ...
Type<List<?>> listOfUnkType = Types.forClass(List.class);
```


Parameterized type from class:

```java
Type<List<String>> listOfStrType =
        Types.forClass(
                List.class,
                TypeParameters.forType(Types.STRING));
// ...
Type<Map<String,? extends Number>> mapOfStrExtNumberType =
        Types.forClass(
                Map.class,
                TypeParameters.forType(Types.STRING),
                TypeParameters.forExtendsType(Types.NUMBER));
```


#### 3) From name

Types can be parsed from canonical or short names. For brevity, classes in `java.lang`, `java.util`, `java.math` 
and `java.io` can omit their package names:

```java
Type<String> strType = (Type<String>) Types.forName("String");
// ...
Type<Map<String,? extends Number>> mapOfStrExtNumberType =
        (Type<Map<String,? extends Number>>) Types.forName("Map<String,? extends Number>");
// Fully-qualified when necessary 
Type urlType = (Type<URL>) Types.forName("java.net.URL");
Type<String> strType = (Type<String>) Types.forName("String");
```


#### 4) From `java.lang.reflect.Type`

Types can be built from reflection `Type` instances (e.g., `Method#getGenericReturnType()`):


```java
Method aMethod = SomeClassOfMine.class.getMethod("toString");
java.lang.reflect.Type javaLangReflectType = aMethod.getGenericReturnType();
Type<String> strType = (Type<String>) Types.forJavaLangReflectType(javaLangReflectType);
```


Variable substitution when generics include unresolved variables:

```java
public class DataClass { public <E> Map<String,List<E>> getData() { /* ... */ return null; } }
// ...
Method getDataMethod = DataClass.class.getMethod("getData");
java.lang.reflect.Type returnType = getDataMethod.getGenericReturnType();
// Resolve E -> Integer
Map<String,Type<?>> variables = new HashMap<>();
variables.put("E", Types.INTEGER);
// type will be "Map<String,List<Integer>>"
Type<?> type = Types.forJavaLangReflectType(returnType, variables);
// Or using a cast, if we prefer to be exact:
Type<Map<String,List<Integer>>> preciseType = (Type<Map<String,List<Integer>>>) Types. forJavaLangReflectType(returnType, variables);

```



### Parameterized Types from Parameters

`Types` provides factories to build commonly parameterized shapes from component types (both with `Type<T>` and
with `TypeParameter<T>` for wildcards/bounds).

| Transformation              | Factory Method                                                                              |
|:----------------------------|:--------------------------------------------------------------------------------------------|
| `T` → `T[]`                 | `Types.arrayOf(Type<T>)`                                                                    |
| `T` → `Iterable<T>`         | `Types.iterableOf(Type<T>)` / `Types.iterableOf(TypeParameter<T>)`                          |
| `T` → `Class<T>`            | `Types.classOf(Type<T>)` / `Types.classOf(TypeParameter<T>)`                                |
| `T` → `Collection<T>`       | `Types.collectionOf(Type<T>)` / `Types.collectionOf(TypeParameter<T>)`                      |
| `T` → `Comparator<T>`       | `Types.comparatorOf(Type<T>)` / `Types.comparatorOf(TypeParameter<T>)`                      |
| `T` → `Enumeration<T>`      | `Types.enumerationOf(Type<T>)` / `Types.enumerationOf(TypeParameter<T>)`                    |
| `T` → `Iterator<T>`         | `Types.iteratorOf(Type<T>)` / `Types.iteratorOf(TypeParameter<T>)`                          |
| `T` → `List<T>`             | `Types.listOf(Type<T>)` / `Types.listOf(TypeParameter<T>)`                                  |
| `T` → `ListIterator<T>`     | `Types.listIteratorOf(Type<T>)` / `Types.listIteratorOf(TypeParameter<T>)`                  |
| `K`,`V` → `Map<K,V>`        | `Types.mapOf(Type<K>,Type<V>)` / `Types.mapOf(TypeParameter<K>,TypeParameter<V>)`           |
| `K`,`V` → `Map.Entry<K,V>`  | `Types.mapEntryOf(Type<K>,Type<V>)` / `Types.mapEntryOf(TypeParameter<K>,TypeParameter<V>)` |
| `T` → `Queue<T>`            | `Types.queueOf(Type<T>)` / `Types.queueOf(TypeParameter<T>)`                                |
| `T` → `Set<T>`              | `Types.setOf(Type<T>)` / `Types.setOf(TypeParameter<T>)`                                    |

Example:

```java
Type<String[]> strArrType = Types.arrayOf(Types.STRING);
```



### Component Types out of Parameterized Types

`Types` also offers utility extractors to get component types from parameterized types:

| Transformation          | Factory Method                                         |
|:------------------------|:-------------------------------------------------------|
| `T[]` → `T`             | `Types.arrayComponentOf(Type<T[]>)`                    |
| `Iterable<T>` → `T`     | `Types.iterableComponentOf(Type<Iterable<T>>)`         |
| `Class<T>` → `T`        | `Types.classComponentOf(Type<Class<T>>)`               |
| `Collection<T>` → `T`   | `Types.collectionComponentOf(Type<Collection<T>>)`     |
| `Comparator<T>` → `T`   | `Types.comparatorComponentOf(Type<Comparator<T>>)`     |
| `Enumeration<T>` → `T`  | `Types.enumerationComponentOf(Type<Enumeration<T>>)`   |
| `Iterator<T>` → `T`     | `Types.iteratorComponentOf(Type<Iterator<T>>)`         |
| `List<T>` → `T`         | `Types.listComponentOf(Type<List<T>>)`                 |
| `ListIterator<T>` → `T` | `Types.listIteratorComponentOf(Type<ListIterator<T>>)` |
| `Map<K,V>` → `K`        | `Types.mapKeyComponentOf(Type<Map<K,V>>)`              |
| `Map<K,V>` → `V`        | `Types.mapValueComponentOf(Type<Map<K,V>>)`            |
| `Map.Entry<K,V>` → `K`  | `Types.mapEntryKeyComponentOf(Type<Map.Entry<K,V>>)`   |
| `Map.Entry<K,V>` → `V`  | `Types.mapEntryValueComponentOf(Type<Map.Entry<K,V>>)` |
| `Queue<T>` → `T`        | `Types.queueComponentOf(Type<Queue<T>>)`               |
| `Set<T>` → `T`          | `Types.setComponentOf(Type<Set<T>>)`                   |


```java
Type<String> strType = Types.arrayComponentOf(Types.ARRAY_OF_STRING);
```

